package samples.aalamir.customcalendar.datepickerlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import samples.aalamir.customcalendar.R;

/**
 * Created by daffodil on 18/1/17.
 */
public class CustomSpinner extends LinearLayout {
    private Context mContext;
    private View view;
    private LinearLayout mSpinnerHandler;
    private TextView mHandlerLabel;
    private Spinner mSpinner;
    private CustomSpinnerAdapter mAdapter;
    private SpinnerListener mListener;
    private String selectedItem;

    public CustomSpinner(Context context) {
        super(context);
        mContext = context;
        initializeSpinner();
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initializeSpinner();
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initializeSpinner();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initializeSpinner();
    }

    private void initializeSpinner(){
        final LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflate.inflate(R.layout.custom_spinner, this, true);
        mSpinnerHandler=(LinearLayout)view.findViewById(R.id.spinner_controller);
        mHandlerLabel=(TextView)mSpinnerHandler.findViewById(R.id.tv_handler_label);
        mSpinner=(Spinner)view.findViewById(R.id.spinner);

        mSpinnerHandler.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinner.performClick();
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String)adapterView.getItemAtPosition(i);
                mHandlerLabel.setText(selectedItem);
                if (mListener!=null){
                    mListener.onItemChanged((String)adapterView.getItemAtPosition(i),i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public String getSelectedItem(){
        return selectedItem;

    }
    public void setData(List<String> data){
        mAdapter=new CustomSpinnerAdapter(mContext, R.layout.custom_spinner_item,data);
        mSpinner.setAdapter(mAdapter);
    }

    public void setSelectedItem(String item){
        if (mAdapter!=null) {
            int index = mAdapter.getIndexByData(item);
            if (index >= 0) {
                mSpinner.setSelection(index);
            }
        }
    }

    public void setOnItemChangedListener(SpinnerListener listener){
        mListener=listener;
    }
}
