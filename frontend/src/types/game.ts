export type Store = 'STEAM' | 'EPIC' | 'NUUVEM' | 'GOG';

export interface Price {
  store: Store;
  price: number;
  currency: string;
}

export interface GameSummary {
  id: string;
  slug: string;
  name: string;
  coverUrl: string | null;
  genres: string[];
  platforms: string[];
  lowestPrice: Price | null;
}

export interface StoreListing {
  store: Store;
  price: number | null;
  currency: string;
  externalUrl: string | null;
  capturedAt: string;
}

export interface GameDetail {
  id: string;
  slug: string;
  name: string;
  coverUrl: string | null;
  releaseDate: string | null;
  genres: string[];
  platforms: string[];
  storeListings: StoreListing[];
}

export interface PageMeta {
  page: number;
  perPage: number;
  total: number;
  hasMore: boolean;
}

export interface PagedResponse<T> {
  data: T[];
  meta: PageMeta;
  requestId: string;
}

export interface ApiResponse<T> {
  data: T;
  requestId: string;
}

export interface ApiErrorDetail {
  field: string;
  issue: string;
}

export interface ApiErrorBody {
  code: string;
  message: string;
  details: ApiErrorDetail[];
}
