import { Link } from 'react-router-dom';

export function NotFoundPage() {
  return (
    <div className="flex flex-col items-center justify-center gap-2 py-24 text-center">
      <h1 className="text-4xl font-semibold">404</h1>
      <p className="text-neutral-400">Essa página não existe.</p>
      <Link
        to="/"
        className="mt-4 rounded-full bg-violet-500/15 px-4 py-1.5 text-sm text-violet-300 hover:bg-violet-500/25"
      >
        Voltar ao catálogo
      </Link>
    </div>
  );
}
