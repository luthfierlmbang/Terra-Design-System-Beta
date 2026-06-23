import { AppShell } from './components/AppShell';
import { PresentationDeck } from './components/PresentationDeck';
import { terraAiWorkflowDeck } from './content/presentations/terraAiWorkflow';
import { registry } from './content/registry';
import { useRoute } from './lib/router';

export function App() {
  const route = useRoute();

  if (route.startsWith('/deck/terra-ai-workflow')) {
    return <PresentationDeck deck={terraAiWorkflowDeck} route={route} />;
  }

  const workspaceId = route.startsWith('/oasis') ? 'oasis' : 'terra';
  return <AppShell entries={registry} route={route} workspaceId={workspaceId} />;
}
