import { Link } from 'react-router-dom';
import { formatPrice } from '../lib/format';
import { STORE_META } from '../lib/store';
import type { GameSummary } from '../types/game';

interface GameCardProps {
  game: GameSummary;
}

export function GameCard({ game }: GameCardProps) {
  return (
    <Link
      to={`/jogos/${game.slug}`}
      className="group flex flex-col overflow-hidden rounded-xl bg-neutral-900 ring-1 ring-neutral-800 transition hover:ring-neutral-600"
    >
      <div className="aspect-[2/3] w-full overflow-hidden bg-neutral-800">
        {game.coverUrl && (
          <img
            src={game.coverUrl}
            alt={game.name}
            loading="lazy"
            className="h-full w-full object-cover transition duration-300 group-hover:scale-105"
          />
        )}
      </div>
      <div className="flex flex-1 flex-col gap-2 p-3">
        <h3 className="line-clamp-2 text-sm font-medium text-neutral-100">{game.name}</h3>
        <div className="flex flex-wrap gap-1">
          {game.genres.slice(0, 2).map((genre) => (
            <span key={genre} className="rounded-full bg-neutral-800 px-2 py-0.5 text-[11px] text-neutral-400">
              {genre}
            </span>
          ))}
        </div>
        <div className="mt-auto pt-1">
          {game.lowestPrice ? (
            <div className="flex items-center justify-between gap-2">
              <span
                className={`rounded-full px-2.5 py-1 text-[11px] font-medium ${STORE_META[game.lowestPrice.store].badgeClass}`}
              >
                {STORE_META[game.lowestPrice.store].label}
              </span>
              <span className="text-sm font-semibold text-emerald-400">
                {formatPrice(game.lowestPrice.price, game.lowestPrice.currency)}
              </span>
            </div>
          ) : (
            <span className="text-xs text-neutral-500">Preço não disponível</span>
          )}
        </div>
      </div>
    </Link>
  );
}
