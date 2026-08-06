export function EmptyState({ message }: { message: string }) {
  return (
    <div className="rounded-xl border border-neutral-800 bg-neutral-900/50 p-8 text-center text-sm text-neutral-400">
      {message}
    </div>
  );
}
