/**
 * Created by Vladislav Rudev on 12/15/2017.
 */
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Question {
    public static void main(String[] args) {
        new Question(System.in, System.out);
    }

    static class Solver {
        int n;
        Interval[] interval;
        InputReader in;
        PrintWriter out;

        void solve()
        {
            n= in.nextInt();
            interval=new Interval[n];
            for (int i = 0; i < n; i++) {
                interval[i]=new Interval(in.nextInt(),in.nextInt(),i+1);


            }

            Arrays.sort(interval, new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    if (o1.x == o2.x)
                        return Integer.compare(o2.y, o1.y);

                    return Integer.compare(o1.x, o2.x);
                }
            });

//            int temp_x=interval[0].x,temp_y=interval[0].y;
//            for (int i = 1; i < n; i++) {
//                if(temp_x<=interval[i].x && temp_y>=interval[i].y)
//                {
//                     out.println(interval[i].z);
//                    return;
//                }
//                else if(temp_x<=interval[i].x && temp_y<interval[i].y)
//                {
//                    temp_y=interval[i].y;
//                }
//                else if(temp_x==interval[i].y)
//                {
//                    temp_y=interval[i].y;
//                }
//                else if(temp_x>interval[i].y)
//                {
//                    temp_x=interval[i].x;
//                    temp_y=interval[i].y;
//                }
//            }
            for (int i = 0; i <n-1; i++) {
                if(interval[i].x==interval[i+1].x)
                {
                    out.println(interval[i+1].z);
                    return;
                }
            }
            int curr=1,prev=0,next=2;
            while(next<n)
            {
                if(interval[curr].x==interval[prev].x)
                {
                    out.println(interval[curr].z);
                    return;
                }
                else if(interval[curr].x>interval[prev].x && interval[curr].y<=interval[prev].y)
                {
                    out.println(interval[curr].z);
                    return;
                }
                else if(interval[curr].x>interval[prev].x && interval[curr].x<=interval[prev].y)
                {
                    if(interval[next].x>interval[curr].x && interval[next].x<=interval[curr].y)
                    {
                        if(interval[next].y<=interval[curr].y)
                        {
                            out.println(interval[next].z);
                            return;
                        }
                        else if(interval[next].x<=interval[prev].y||(interval[curr].y-interval[curr].x==1)||(interval[next].x-interval[prev].y==1))
                        {
                            out.println(interval[curr].z);
                            return;
                        }
                        else
                        {
                            prev=next;
                            curr=prev+1;
                            next=curr+1;
                        }

                    }
                    else
                    {
                        prev=next;
                        curr=prev+1;
                        next=curr+1;
                    }
                }
                else
                {
                    next++;
                    prev++;
                    curr++;
                }
            }
            if(curr<n && prev<n &&interval[curr].x>interval[prev].x && interval[curr].y<=interval[prev].y)
            {
                out.println(interval[curr].z);
                return;
            }
            out.println(-1);
        }


        class Interval
        {
            int x, y,z;

            public Interval(int x, int y, int z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }
            /*            Interval(int x,int y,int z){
                this.x=
            }*/
        }



        Solver(InputReader in, PrintWriter out) {
            this.in = in;
            this.out = out;
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }

            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();

                res *= 10;
                res += c & 15;

                c = read();
            } while (!isSpaceChar(c));

            return res * sgn;
        }

        public int[] nextIntArray(int arraySize) {
            int array[] = new int[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextInt();

            return array;
        }

        public long nextLong() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sign = 1;

            if (c == '-') {
                sign = -1;

                c = read();
            }

            long result = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();

                result *= 10;
                result += c & 15;

                c = read();
            } while (!isSpaceChar(c));

            return result * sign;
        }

        public long[] nextLongArray(int arraySize) {
            long array[] = new long[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextLong();

            return array;
        }

        public float nextFloat() {
            float result, div;
            byte c;

            result = 0;
            div = 1;
            c = (byte) read();

            while (c <= ' ')
                c = (byte) read();

            boolean isNegative = (c == '-');

            if (isNegative)
                c = (byte) read();

            do {
                result = result * 10 + c - '0';
            } while ((c = (byte) read()) >= '0' && c <= '9');

            if (c == '.')
                while ((c = (byte) read()) >= '0' && c <= '9')
                    result += (c - '0') / (div *= 10);

            if (isNegative)
                return -result;

            return result;
        }

        public double nextDouble() {
            double ret = 0, div = 1;
            byte c = (byte) read();

            while (c <= ' ')
                c = (byte) read();

            boolean neg = (c == '-');

            if (neg)
                c = (byte) read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = (byte) read()) >= '0' && c <= '9');

            if (c == '.')
                while ((c = (byte) read()) >= '0' && c <= '9')
                    ret += (c - '0') / (div *= 10);

            if (neg)
                return -ret;

            return ret;
        }

        public String next() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            StringBuilder res = new StringBuilder();

            do {
                res.appendCodePoint(c);

                c = read();
            } while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String nextLine() {
            int c = read();

            StringBuilder result = new StringBuilder();

            do {
                result.appendCodePoint(c);

                c = read();
            } while (!isNewLine(c));

            return result.toString();
        }

        public boolean isNewLine(int c) {
            return c == '\n';
        }

        public void close() {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

    }

    Question(InputStream inputStream, OutputStream outputStream) {
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver(in, out);

        solver.solve();
        in.close();
        out.flush();
        out.close();
    }

}

/*

3
1 3
2 9
7 11

*/
