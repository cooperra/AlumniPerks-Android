package edu.rosehulman.roseperks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogFragmentHelp extends DialogFragment {
	
	@Override 
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder aboutMessage = new AlertDialog.Builder(getActivity());
		aboutMessage.setTitle(R.string.help_title);
		aboutMessage.setIcon(R.drawable.btn_help);
		aboutMessage.setMessage(R.string.help_message);
		aboutMessage.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		return aboutMessage.create();
	}
}
