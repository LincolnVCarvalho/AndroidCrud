package adapters;

/**
 * Created by LincolnV on 16/03/2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.*;
import entities.*;
import java.util.*;
import android.view.*;

import br.com.lincolnv.androidcrud.R;


public class ContactListAdapter extends ArrayAdapter<Contact>   {

    private Context context;
    private List<Contact> contacts;

    public  ContactListAdapter(Context context, List<Contact> contacts){
        super(context, R.layout.contact_layout, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, View converterView, ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.contact_layout, parent, false);
        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(contacts.get(position).getName());
        TextView textViewPhone = (TextView) view.findViewById(R.id.textViewPhone);
        textViewPhone.setText(contacts.get(position).getPhone());
        return view;
    }
}
