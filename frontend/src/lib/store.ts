import type { Store } from '../types/game';

interface StoreMeta {
  label: string;
  badgeClass: string;
}

export const STORE_META: Record<Store, StoreMeta> = {
  STEAM: { label: 'Steam', badgeClass: 'bg-sky-500/15 text-sky-300' },
  EPIC: { label: 'Epic Games', badgeClass: 'bg-neutral-500/15 text-neutral-200' },
  NUUVEM: { label: 'Nuuvem', badgeClass: 'bg-amber-500/15 text-amber-300' },
  GOG: { label: 'GOG', badgeClass: 'bg-purple-500/15 text-purple-300' },
};
