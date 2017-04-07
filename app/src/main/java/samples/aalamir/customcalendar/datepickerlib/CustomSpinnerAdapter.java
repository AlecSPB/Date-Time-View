package samples.aalamir.customcalendar.datepickerlib;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daffodil on 18/1/17.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> data=new ArrayList<>();

    public CustomSpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        data=objects;
    }

    public int getIndexByData(String item){
        return data.indexOf(item);
    }
}
