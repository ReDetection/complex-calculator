package calc.math;

/*************************************************************************
 *  Data type for complex numbers.
 *
 *  The data type is "immutable" so once you create and initialize
 *  a Complex object, you cannot change it. The "final" keyword
 *  when declaring re and im enforces this rule, making it a
 *  compile-time error to change the .re or .im fields after
 *  they've been initialized.
 *
 *  % java Complex
 *  a            = 5.0 + 6.0i
 *  b            = -3.0 + 4.0i
 *  Re(a)        = 5.0
 *  Im(a)        = 6.0
 *  b + a        = 2.0 + 10.0i
 *  a - b        = 8.0 + 2.0i
 *  a * b        = -39.0 + 2.0i
 *  b * a        = -39.0 + 2.0i
 *  a / b        = 0.36 - 1.52i
 *  (a / b) * b  = 5.0 + 6.0i
 *  conj(a)      = 5.0 - 6.0i
 *  |a|          = 7.810249675906654
 *  tan(a)       = -6.685231390246571E-6 + 1.0000103108981198i
 *  
 *  copied from unknown site and changed some code
 *
 *************************************************************************/

public class Complex {
    private final double re;   // the real part
    private final double im;   // the imaginary part
    private final String string;

    // create a new object with the given real and imaginary parts
    /**
     * @deprecated use fromAlg and fromAlg methods
     */
    public Complex(double arg0, double arg1){
		re = 0;
		im = 0;
		string = "";
	}
    
    private Complex(double arg0, double arg1, boolean isExp) {
        if(isExp){
        	arg1=Math.toRadians(arg1);
        	re=arg0*Math.cos(arg1);
        	im=arg0*Math.sin(arg1);
        	string = toExpString();
        }else{
	        re = arg0;
	        im = arg1;
	        string = toAlgString();
        }
    }
    public static Complex fromAlg(double real, double imaginary){
    	return new Complex(real, imaginary,false);
    	
    }
    public static Complex fromExp(double abs, double phase){
    	return new Complex(abs, phase, true);
    }
    

    // return a string representation of the invoking Complex object
    //TODO:округление до шести знаков после запятой, чтобы не было байды типа 7.9999999999999 L0.0
    public String toString() {
        return string;
    }
    
    public String toAlgString(){
    	double re = ((long)(this.re*100000))/100000.0,im=((long)(this.im*100000))/100000.0;
    	if (re == 0)return im + "i";
        if (im == 0)return re + "";
        if (im <  0)return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }
    public String toExpString(){
    	return ((long)(abs()*100000))/100000.0 + " L" + ((long)(Math.toDegrees(phase())*100000))/100000.0; 
    }

    // return abs/modulus/magnitude and angle/phase/argument
    public double abs()   { return Math.sqrt(re*re + im*im); }  // Math.sqrt(re*re + im*im)
    public double phase() { return mMath.atan2(im, re); }  // between -pi and pi

    // return a new Complex object whose value is (this + b)
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag,false);
        
    }

    // return a new Complex object whose value is (this - b)
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag,false);
    }

    // return a new Complex object whose value is (this * b)
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag,false);
    }

    // scalar multiplication
    // return a new object whose value is (this * alpha)
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im,false);
    }

    // return a new Complex object whose value is the conjugate of this
    public Complex conjugate() {  return new Complex(re, -im,false); }

    // return a new Complex object whose value is the reciprocal of this
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale,false);
    }

    // return the real or imaginary part
    public double re() { return re; }
    public double im() { return im; }

    // return a / b
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    /*// return a new Complex object whose value is the complex exponential of this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // return a new Complex object whose value is the complex sine of this
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex cosine of this
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex tangent of this
    public Complex tan() {
        return sin().divides(cos());
    }*/
    


    // a static version of plus
    public static Complex plus(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag,false);
        return sum;
    }



}
