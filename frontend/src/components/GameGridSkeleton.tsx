function GameCardSkeleton() {
  return (
    <div className="overflow-hidden rounded-xl bg-neutral-900 ring-1 ring-neutral-800">
      <div className="aspect-[2/3] w-full animate-pulse bg-neutral-800" />
      <div className="space-y-2 p-3">
        <div className="h-4 w-3/4 animate-pulse rounded bg-neutral-800" />
        <div className="h-3 w-1/2 animate-pulse rounded bg-neutral-800" />
      </div>
    </div>
  );
}

export function GameGridSkeleton({ count = 10 }: { count?: number }) {
  return (
    <div className="grid grid-cols-2 gap-4 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5">
      {Array.from({ length: count }, (_, index) => (
        <GameCardSkeleton key={index} />
      ))}
    </div>
  );
}
