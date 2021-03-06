package courier.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiananshop.a.R;

import java.util.List;

import courier.model.ConsumeLogInfo;

/**
 * 充值记录
 * Created by Administrator on 2018/1/10.
 */
public class ConsumeLogAdapter extends BaseAdapter {
    private Context mcontext;
    private List<ConsumeLogInfo.datas.list> list;

    public ConsumeLogAdapter(Context mcontext, List<ConsumeLogInfo.datas.list> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    /**
     * 刷新
     * @param list
     */
    public void refreshData(List<ConsumeLogInfo.datas.list> list){
        if (list==null)return;
        this.list=list;
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     * @param list
     */
    public void addData(List<ConsumeLogInfo.datas.list> list){
        if (list==null)return;
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list==null?null:list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.item_topuplog, null);
            new ViewHodler(convertView);
        }
        ViewHodler holder = (ViewHodler) convertView.getTag();
        ConsumeLogInfo.datas.list list = this.list.get(position);
        holder.tv_type.setText(list.name);
        holder.tv_balance.setText("余额 "+list.zong_money);
        holder.tv_money.setText(list.money+"元");
        holder.tv_time.setText(TimeStamp2Date(list.addtime,"yyyy-MM-dd HH:mm"));
        return convertView;
    }

    class ViewHodler {
        TextView tv_type, tv_balance, tv_money, tv_time;

        public ViewHodler(View convertView) {
            tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            tv_balance = (TextView) convertView.findViewById(R.id.tv_balance);
            tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(this);
        }
    }
    /**
     * 时间戳转时间
     *
     * @param timestampString
     *            时间戳
     * @param formats
     *            格式(yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public String TimeStamp2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat(formats)
                .format(new java.util.Date(timestamp));
        return date;
    }
}
