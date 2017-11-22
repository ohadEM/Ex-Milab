package layout;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class QuotesService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "layout.action.FOO";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "layout.extra.PARAM1";
    private static final String EXTRA_PARAM_QUOTE_STRING = "layout.extra.QUOTE";
    private static final String EXTRA_PARAM_RECEIVER = "layout.extra.RECEIVER";

    public QuotesService() {
        super("QuotesService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, int param1) {
        Intent intent = new Intent(context, QuotesService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        context.startService(intent);
    }

    @Override
    /* Intents go. */
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                ResultReceiver receiver = intent.getParcelableExtra(EXTRA_PARAM_RECEIVER);
                handleActionFoo(param1, receiver);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, ResultReceiver receiver) {
        int random = (int) (Math.random() * 5);
        String quote;
        switch (random) {
            case 0:
                quote = "Surrender now or fall forever!";
                break;
            case 1:
                quote = "You hit like a vegetarian!";
                break;
            case 2:
                quote = "I'm proud of you, you are trying so very hard!";
                break;
            case 3:
                quote = "Someone is getting a bit grumpy";
                break;
            case 4:
                quote = "Shot to the heart and I'm to blame, baby!";
                break;
            case 5:
                quote = "Intelligence is not always everything. In fact, in your case, it's nothing!";
                break;
            default:
                quote = "Aww! Is that the best you can do?";
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_PARAM_QUOTE_STRING, quote);
        receiver.send(0, bundle);
    }
}