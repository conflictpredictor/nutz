package org.nutz.lang.tmpl;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public class TmplJsonEle extends TmplDynamicEle {

    private JsonFormat _fmt;

    public TmplJsonEle(String key, String fmt, String dft_str) {
        super("json", key, fmt, dft_str);

        _fmt = JsonFormat.forLook();
        if (null != fmt) {
            _fmt.setCompact(fmt.indexOf('c') >= 0);
            _fmt.setQuoteName(fmt.indexOf('q') >= 0);
            _fmt.setIgnoreNull(fmt.indexOf('n') < 0);
        }
    }

    @Override
    protected String _val(Object val) {
        if (null == val)
            return "null";

        if (val instanceof CharSequence){
            return val.toString();
        }

        return Json.toJson(val, _fmt);
    }

}
