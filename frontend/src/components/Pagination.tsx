import type { PageMeta } from '../types/game';

interface PaginationProps {
  meta: PageMeta;
  onPageChange: (page: number) => void;
  isFetching?: boolean;
}

export function Pagination({ meta, onPageChange, isFetching }: PaginationProps) {
  const totalPages = Math.max(1, Math.ceil(meta.total / meta.perPage));

  return (
    <div className="flex items-center justify-center gap-3 pt-2 text-sm text-neutral-400">
      <button
        type="button"
        disabled={meta.page === 0}
        onClick={() => onPageChange(meta.page - 1)}
        className="rounded-full px-3 py-1 ring-1 ring-neutral-800 transition hover:ring-neutral-600 disabled:opacity-40 disabled:hover:ring-neutral-800"
      >
        Anterior
      </button>
      <span>
        {meta.page + 1} de {totalPages}
        {isFetching && ' · atualizando…'}
      </span>
      <button
        type="button"
        disabled={!meta.hasMore}
        onClick={() => onPageChange(meta.page + 1)}
        className="rounded-full px-3 py-1 ring-1 ring-neutral-800 transition hover:ring-neutral-600 disabled:opacity-40 disabled:hover:ring-neutral-800"
      >
        Próxima
      </button>
    </div>
  );
}
