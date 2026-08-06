-- Ludex initial schema: users, catalog (game/genre/platform), reviews, pricing.

CREATE TYPE store_type AS ENUM ('STEAM', 'EPIC', 'NUUVEM', 'GOG');

CREATE TABLE app_user (
    id            UUID PRIMARY KEY,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name  VARCHAR(100) NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at    TIMESTAMPTZ
);

CREATE UNIQUE INDEX uq_app_user_email ON app_user (email) WHERE deleted_at IS NULL;

CREATE TABLE game (
    id            UUID PRIMARY KEY,
    slug          VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    cover_url     TEXT,
    release_date  DATE,
    rawg_id       BIGINT,
    igdb_id       BIGINT,
    steam_app_id  BIGINT,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at    TIMESTAMPTZ
);

CREATE UNIQUE INDEX uq_game_slug ON game (slug) WHERE deleted_at IS NULL;
CREATE UNIQUE INDEX uq_game_rawg_id ON game (rawg_id) WHERE deleted_at IS NULL AND rawg_id IS NOT NULL;
CREATE UNIQUE INDEX uq_game_igdb_id ON game (igdb_id) WHERE deleted_at IS NULL AND igdb_id IS NOT NULL;
CREATE UNIQUE INDEX uq_game_steam_app_id ON game (steam_app_id) WHERE deleted_at IS NULL AND steam_app_id IS NOT NULL;

CREATE TABLE genre (
    id         UUID PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_genre_name UNIQUE (name)
);

CREATE TABLE platform (
    id         UUID PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_platform_name UNIQUE (name)
);

CREATE TABLE game_genres (
    game_id  UUID NOT NULL REFERENCES game (id),
    genre_id UUID NOT NULL REFERENCES genre (id),
    PRIMARY KEY (game_id, genre_id)
);

CREATE INDEX idx_game_genres_genre_id ON game_genres (genre_id);

CREATE TABLE game_platforms (
    game_id     UUID NOT NULL REFERENCES game (id),
    platform_id UUID NOT NULL REFERENCES platform (id),
    PRIMARY KEY (game_id, platform_id)
);

CREATE INDEX idx_game_platforms_platform_id ON game_platforms (platform_id);

CREATE TABLE review (
    id         UUID PRIMARY KEY,
    game_id    UUID NOT NULL REFERENCES game (id),
    user_id    UUID NOT NULL REFERENCES app_user (id),
    rating     SMALLINT NOT NULL,
    body       TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ,
    CONSTRAINT ck_review_rating CHECK (rating BETWEEN 1 AND 5)
);

CREATE INDEX idx_review_game_id ON review (game_id);
CREATE INDEX idx_review_user_id ON review (user_id);
CREATE UNIQUE INDEX uq_review_game_user ON review (game_id, user_id) WHERE deleted_at IS NULL;

CREATE TABLE store_listing (
    id            UUID PRIMARY KEY,
    game_id       UUID NOT NULL REFERENCES game (id),
    store         store_type NOT NULL,
    external_id   VARCHAR(255),
    external_url  TEXT,
    current_price NUMERIC(10, 2),
    currency      VARCHAR(3) NOT NULL DEFAULT 'BRL',
    captured_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_store_listing_game_store UNIQUE (game_id, store)
);

CREATE INDEX idx_store_listing_game_id ON store_listing (game_id);

CREATE TABLE price_history (
    id               UUID PRIMARY KEY,
    store_listing_id UUID NOT NULL REFERENCES store_listing (id),
    price            NUMERIC(10, 2) NOT NULL,
    currency         VARCHAR(3) NOT NULL,
    captured_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    created_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_price_history_listing_captured ON price_history (store_listing_id, captured_at DESC);

CREATE TABLE wishlist (
    user_id    UUID NOT NULL REFERENCES app_user (id),
    game_id    UUID NOT NULL REFERENCES game (id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id, game_id)
);

CREATE INDEX idx_wishlist_game_id ON wishlist (game_id);
