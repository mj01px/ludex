import { keepPreviousData, useQuery } from '@tanstack/react-query';
import { getGame, listGames, type ListGamesParams } from '../api/games';

export function useGamesQuery(params: ListGamesParams) {
  return useQuery({
    queryKey: ['games', params],
    queryFn: () => listGames(params),
    placeholderData: keepPreviousData,
  });
}

export function useGameDetailQuery(slug: string | undefined) {
  return useQuery({
    queryKey: ['game', slug],
    queryFn: () => getGame(slug as string),
    enabled: Boolean(slug),
  });
}
