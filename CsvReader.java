class CsvReader implements java.util.Iterator {
    private String csvFileName = null;
    private java.io.BufferedReader csvBufferedReader = null;
    private String line = null;

    static public void main(String args[]) {
        System.out.println(args[0]);
        CsvReader reader = new CsvReader(args[0]);

        while (reader.hasNext()) {
            var data = (String[])reader.next();
            for (var e : data) {
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public CsvReader(String csvFileName) {
        try {
            this.csvFileName = csvFileName;
            var file = new java.io.File(this.csvFileName);
            var reader = new java.io.FileReader(file);
            this.csvBufferedReader = new java.io.BufferedReader(reader);
            this.line = this.csvBufferedReader.readLine();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            this.csvFileName = null;
            this.csvBufferedReader = null;
        }
    }

    public boolean hasNext() {
        return line != null;
    }

    public Object next() {
        try {
            var result = line.split("\s*,\s*");

            line = csvBufferedReader.readLine();

            return result;
        } catch (java.io.IOException | java.lang.NullPointerException ex) {
            ex.printStackTrace();
            this.csvFileName = null;
            this.csvBufferedReader = null;
            throw new java.util.NoSuchElementException();
        }
    }
}
