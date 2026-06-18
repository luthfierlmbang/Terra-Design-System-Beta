import { AppShell } from './components/AppShell';
import { registry } from './content/registry';
import { useRoute } from './lib/router';

export function App() {
  const route = useRoute();
  return <AppShell entries={registry} route={route} />;
}
