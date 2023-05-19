public class ExceptionTest {

    public int testRunning(int val) {
        try {
            if(val == 2) {
                throw new Exception("just kidding");
            }
            else {
                if(val == 0)
                throw new RuntimeException("again kidding");
                val = 200;

            }

        }
        catch (RuntimeException e) {
            System.out.println("I got you RuntimeException" + e);
            //val = 29;
            return 29;
        }
        catch (Exception e) {
            System.out.println("I got you Exception " + e);
            val = 20;
          //  return 30;
        }
        finally {
            System.out.println("finally executed");
            //val = 1000;
            //return val;
        }
        //val = 21;
       System.out.println("before returning");
        return val;
    }

    public  void main(String args[]) {
        ExceptionTest exceptionTest = new ExceptionTest();
        int result = exceptionTest.testRunning(2);
        System.out.println(result);
    }
}
