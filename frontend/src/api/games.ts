import { api } from '../lib/api';
import type { ApiResponse, GameDetail, GameSummary, PagedResponse } from '../types/game';

export interface ListGamesParams {
  search?: string;
  page?: number;
  perPage?: number;
}

export function listGames(params: ListGamesParams = {}): Promise<PagedResponse<GameSummary>> {
  const query = new URLSearchParams();
  if (params.search) query.set('search', params.search);
  if (params.page !== undefined) query.set('page', String(params.page));
  if (params.perPage !== undefined) query.set('perPage', String(params.perPage));

  const qs = query.toString();
  return api.get<PagedResponse<GameSummary>>(`/api/v1/games${qs ? `?${qs}` : ''}`);
}

export function getGame(slug: string): Promise<ApiResponse<GameDetail>> {
  return api.get<ApiResponse<GameDetail>>(`/api/v1/games/${encodeURIComponent(slug)}`);
}
