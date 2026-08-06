export function GameDetailSkeleton() {
  return (
    <div className="flex animate-pulse flex-col gap-8 md:flex-row">
      <div className="aspect-[2/3] w-full max-w-xs shrink-0 rounded-xl bg-neutral-900" />
      <div className="flex-1 space-y-4">
        <div className="h-8 w-2/3 rounded bg-neutral-900" />
        <div className="h-4 w-1/3 rounded bg-neutral-900" />
        <div className="h-40 rounded bg-neutral-900" />
      </div>
    </div>
  );
}
