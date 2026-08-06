import { Link } from 'react-router-dom';

export function Header() {
  return (
    <header className="sticky top-0 z-10 border-b border-neutral-900 bg-neutral-950/80 backdrop-blur">
      <div className="mx-auto flex max-w-6xl items-center justify-between px-4 py-4">
        <Link to="/" className="text-xl font-semibold tracking-tight text-neutral-100">
          Lud<span className="text-violet-400">ex</span>
        </Link>
        <p className="hidden text-sm text-neutral-500 sm:block">
          Compare preços. Encontre seu próximo jogo.
        </p>
      </div>
    </header>
  );
}
