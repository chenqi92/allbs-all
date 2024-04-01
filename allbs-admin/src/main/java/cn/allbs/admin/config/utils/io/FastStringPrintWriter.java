package cn.allbs.admin.config.utils.io;

import cn.allbs.admin.config.constant.CharPool;

import java.io.PrintWriter;

/**
 * 类 FastStringPrintWriter
 *
 * @author ChenQi
 * @date 2024/4/1
 */
public class FastStringPrintWriter extends PrintWriter {
    private final FastStringWriter writer;

    public FastStringPrintWriter() {
        this(256);
    }

    public FastStringPrintWriter(int initialSize) {
        super(new FastStringWriter(initialSize));
        this.writer = (FastStringWriter) out;
    }

    /**
     * Throwable printStackTrace，只掉用了该方法
     *
     * @param x Object
     */
    @Override
    public void println(Object x) {
        writer.write(String.valueOf(x));
        writer.write(CharPool.NEWLINE);
    }

    @Override
    public String toString() {
        return writer.toString();
    }
}
