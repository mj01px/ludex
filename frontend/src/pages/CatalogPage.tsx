import { useEffect, useState } from 'react';
import { ApiError } from '../lib/api';
import { EmptyState } from '../components/EmptyState';
import { ErrorState } from '../components/ErrorState';
import { GameCard } from '../components/GameCard';
import { GameGridSkeleton } from '../components/GameGridSkeleton';
import { Pagination } from '../components/Pagination';
import { useDebouncedValue } from '../hooks/useDebouncedValue';
import { useGamesQuery } from '../hooks/useGames';

const PER_PAGE = 20;

export function CatalogPage() {
  const [searchInput, setSearchInput] = useState('');
  const [page, setPage] = useState(0);
  const debouncedSearch = useDebouncedValue(searchInput, 350);

  useEffect(() => {
    setPage(0);
  }, [debouncedSearch]);

  const { data, isLoading, isError, error, refetch, isFetching } = useGamesQuery({
    search: debouncedSearch || undefined,
    page,
    perPage: PER_PAGE,
  });

  return (
    <div className="flex flex-col gap-6">
      <div>
        <h1 className="text-2xl font-semibold tracking-tight">Catálogo</h1>
        <p className="mt-1 text-sm text-neutral-400">
          Compare preços entre Steam, Epic, Nuuvem e GOG antes de comprar.
        </p>
      </div>

      <input
        type="search"
        value={searchInput}
        onChange={(event) => setSearchInput(event.target.value)}
        placeholder="Buscar por nome..."
        className="w-full max-w-sm rounded-lg border border-neutral-800 bg-neutral-900 px-4 py-2 text-sm text-neutral-100 placeholder:text-neutral-500 focus:border-violet-500 focus:outline-none"
      />

      {isLoading ? (
        <GameGridSkeleton />
      ) : isError ? (
        <ErrorState
          message={error instanceof ApiError ? error.message : 'Não foi possível carregar o catálogo.'}
          onRetry={() => refetch()}
        />
      ) : data && data.data.length > 0 ? (
        <>
          <div className="grid grid-cols-2 gap-4 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5">
            {data.data.map((game) => (
              <GameCard key={game.id} game={game} />
            ))}
          </div>
          <Pagination meta={data.meta} onPageChange={setPage} isFetching={isFetching} />
        </>
      ) : (
        <EmptyState message="Nenhum jogo encontrado pra essa busca." />
      )}
    </div>
  );
}
