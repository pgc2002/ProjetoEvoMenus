package amsi.dei.estg.ipleiria.evo_menu.Listeners;

import android.content.Context;

public interface LoginListener {
    void onValidadeLogin(Context contexto, final String username, final String password, final String valido);
}
