/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.ct.dainf.grader;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 *
 * @author Wilson
 */
public class DelegateInputStream extends FilterInputStream {

    public DelegateInputStream() {
        super(System.in);
    }

    public DelegateInputStream(InputStream in) {
        super(in);
    }
    
    public void setInput(InputStream in) {
        this.in = in;
    }

    public InputStream getIn() {
        return in;
    }

}
