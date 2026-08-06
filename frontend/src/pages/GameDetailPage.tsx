import { Link, useParams } from 'react-router-dom';
import { ApiError } from '../lib/api';
import { formatDate, formatPrice } from '../lib/format';
import { STORE_META } from '../lib/store';
import { EmptyState } from '../components/EmptyState';
import { ErrorState } from '../components/ErrorState';
import { GameDetailSkeleton } from '../components/GameDetailSkeleton';
import { useGameDetailQuery } from '../hooks/useGames';

export function GameDetailPage() {
  const { slug } = useParams<{ slug: string }>();
  const { data, isLoading, isError, error, refetch } = useGameDetailQuery(slug);

  if (isLoading) {
    return <GameDetailSkeleton />;
  }

  if (isError) {
    const notFound = error instanceof ApiError && error.status === 404;
    return notFound ? (
      <EmptyState message="Esse jogo não está no catálogo." />
    ) : (
      <ErrorState message="Não foi possível carregar esse jogo." onRetry={() => refetch()} />
    );
  }

  if (!data) {
    return null;
  }

  const game = data.data;

  return (
    <div className="flex flex-col gap-8 md:flex-row">
      <div className="w-full max-w-xs shrink-0">
        <div className="overflow-hidden rounded-xl bg-neutral-900 ring-1 ring-neutral-800">
          {game.coverUrl && (
            <img src={game.coverUrl} alt={game.name} className="aspect-[2/3] w-full object-cover" />
          )}
        </div>
      </div>

      <div className="flex-1 space-y-6">
        <div>
          <Link to="/" className="text-sm text-neutral-500 hover:text-neutral-300">
            &larr; Voltar ao catálogo
          </Link>
          <h1 className="mt-2 text-3xl font-semibold tracking-tight">{game.name}</h1>
          <div className="mt-3 flex flex-wrap items-center gap-2 text-xs text-neutral-400">
            {game.releaseDate && <span>{formatDate(game.releaseDate)}</span>}
            {game.genres.map((genre) => (
              <span key={genre} className="rounded-full bg-neutral-800 px-2 py-0.5">
                {genre}
              </span>
            ))}
          </div>
          {game.platforms.length > 0 && (
            <p className="mt-1 text-xs text-neutral-500">{game.platforms.join(' · ')}</p>
          )}
        </div>

        <div>
          <h2 className="text-lg font-medium text-neutral-200">Comparar preços</h2>
          {game.storeListings.length === 0 ? (
            <p className="mt-2 text-sm text-neutral-500">Nenhuma loja com preço cadastrado ainda.</p>
          ) : (
            <ul className="mt-3 divide-y divide-neutral-800 overflow-hidden rounded-xl ring-1 ring-neutral-800">
              {game.storeListings.map((listing, index) => (
                <li
                  key={listing.store}
                  className={`flex items-center justify-between gap-4 bg-neutral-900 px-4 py-3 ${
                    index === 0 ? 'ring-1 ring-inset ring-emerald-500/40' : ''
                  }`}
                >
                  <div className="flex items-center gap-3">
                    <span
                      className={`rounded-full px-2.5 py-1 text-xs font-medium ${STORE_META[listing.store].badgeClass}`}
                    >
                      {STORE_META[listing.store].label}
                    </span>
                    {index === 0 && (
                      <span className="text-xs font-medium text-emerald-400">melhor preço</span>
                    )}
                  </div>
                  <div className="flex items-center gap-4">
                    <span className="text-base font-semibold text-neutral-100">
                      {listing.price !== null ? formatPrice(listing.price, listing.currency) : '—'}
                    </span>
                    {listing.externalUrl && (
                      <a
                        href={listing.externalUrl}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="rounded-full bg-neutral-800 px-3 py-1 text-xs text-neutral-300 hover:bg-neutral-700"
                      >
                        Ver loja
                      </a>
                    )}
                  </div>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  );
}
