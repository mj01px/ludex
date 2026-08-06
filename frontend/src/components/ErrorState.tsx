interface ErrorStateProps {
  message: string;
  onRetry?: () => void;
}

export function ErrorState({ message, onRetry }: ErrorStateProps) {
  return (
    <div className="flex flex-col items-center justify-center gap-3 rounded-xl border border-red-900/40 bg-red-950/20 p-8 text-center">
      <p className="text-sm text-red-300">{message}</p>
      {onRetry && (
        <button
          type="button"
          onClick={onRetry}
          className="rounded-full bg-red-500/15 px-4 py-1.5 text-sm text-red-200 hover:bg-red-500/25"
        >
          Tentar de novo
        </button>
      )}
    </div>
  );
}
