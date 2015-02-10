Feature: Scans application for packages and classes
    As a CS teacher
    I want to test students' programming assignments
    In order to find out if classes have been correctly created

    Background:
        Given the maximum grade is 200
        Given the main class is 'Avaliacao3'
        Given I set the script timeout to 3000
        Given I evaluate 'import utfpr.ct.dainf.grader.*;'
        Given I evaluate 'import utfpr.ct.dainf.if62c.avaliacao.*'
        Given I evaluate 'String testFilePath = ClassLoader.getSystemClassLoader().getResource("testdata.txt").getFile();' 
        And I evaluate 'java.io.File testFile = new java.io.File(testFilePath);'
        Given I evaluate 'String shortTestFilePath = ClassLoader.getSystemClassLoader().getResource("shorttestdata.txt").getFile();' 
        And I evaluate 'java.io.File shortTestFile = new java.io.File(shortTestFilePath);'
        Given I evaluate 'String inputFilePath = ClassLoader.getSystemClassLoader().getResource("input1.txt").getFile();' 
        And I evaluate 'java.io.File inputFile = new java.io.File(inputFilePath);'
    
    Scenario: Turn class CredorComparator into an comparator (20)
        Given I report 'Iniciando avaliação...'
        Given I report 'Avaliando item 1...'
        Given class 'utfpr.ct.dainf.if62c.avaliacao.LancamentoComparator' exists store class in <lancamentoComparatorClass>
        And class <lancamentoComparatorClass> implements 'java.util.Comparator'
        Then award 5 points
        Given class <lancamentoComparatorClass> implements 'java.util.Comparator<utfpr.ct.dainf.if62c.avaliacao.Lancamento>'
        Then award 6 points

    Scenario: Check comparator less than result
        Given I evaluate 'lancTest1 = new Lancamento(1, new java.util.Date(111111), "Lanc 1", 1111.11)'
        Given I evaluate 'lancTest2 = new Lancamento(2, new java.util.Date(222222), "Lanc 2", 2222.22);'
        Given I evaluate 'lancComparator = new LancamentoComparator();'
        And expression 'lancComparator.compare(lancTest1, lancTest2) < 0' evaluates to <true>
        Then award 3 points

    Scenario: Check comparator greater than result
        Given expression 'lancComparator.compare(lancTest2, lancTest1) > 0' evaluates to <true>
        Then award 3 points

    Scenario: Check comparator equal result
        Given expression 'lancComparator.compare(lancTest1, lancTest1) == 0' evaluates to <true>
        Then award 3 points

    Scenario: Implement constructor ProcessaPagamento(File) (20)
        Given I report 'Avaliando item 2...'
        Given class 'utfpr.ct.dainf.if62c.avaliacao.ProcessaLancamentos' exists store class in <processaLancClass>
        And class <processaLancClass> declares field 'reader' save in <readerField>
        And field <readerField> is of type 'java.io.BufferedReader'

    Scenario: Check existent file initialization
        Given I evaluate 'ProcessaLancamentos p1Test = new ProcessaLancamentos(testFile)'
        Then award 10 points

    Scenario: Check non-existent file initialization
        Given I evaluate 'java.io.File dummyFile = new File("/dummy/file.txt")'
        And evaluating 'new ProcessaLancamentos(dummyFile)' throws instance of 'java.io.FileNotFoundException' save in <dummyEx>
        Then award 10 points

    Scenario: Implement constructor ProcessaPagamento(String) (20)
        Given I report 'Avaliando item 3...'

    Scenario: Check existent file initialization
        Given I evaluate 'ProcessaLancamentos p1Test = new ProcessaLancamentos(testFilePath)'
        Then award 10 points

    Scenario: Check non-existent file initialization
        Given evaluating 'new ProcessaLancamentos("/dummy/file.txt")' throws instance of 'java.io.FileNotFoundException' save in <dummyEx>
        Then award 10 points

    Scenario: Implement method getNextLine() (20)
        Given I report 'Avaliando item 4...'
        And class <processaLancClass> declares 'getNextLine()' method save in <getNextLineMethod>
        And member <getNextLineMethod> has 'private' modifier
        And method <getNextLineMethod> returns type 'java.lang.String'
        And I evaluate 'java.io.BufferedReader testReader = new java.io.BufferedReader(new java.io.FileReader(testFile))'
        And I evaluate 'String testLine = testReader.readLine();'
        And I evaluate 'ProcessaLancamentos p2Test = new ProcessaLancamentos(testFile)'
        And I set <getNextLineMethod> accessible
        And expression 'getNextLineMethod.invoke(p2Test, null)' evaluates to <testLine>
        Then award 10 points
        And I evaluate 'testLine = testReader.readLine(); testReader.close()'
        And expression 'getNextLineMethod.invoke(p2Test, null)' evaluates to <testLine>
        Then award 10 points

    Scenario: Implement method processaLinha(String) (20)
        Given I report 'Avaliando item 5...'
        And class <processaLancClass> declares 'processaLinha(java.lang.String)' method save in <processaLinhaMethod>
        And member <processaLinhaMethod> has 'private' modifier
        And method <processaLinhaMethod> returns type 'utfpr.ct.dainf.if62c.avaliacao.Lancamento'
        And I set <processaLinhaMethod> accessible
        And I evaluate 'ProcessaLancamentosTest procLancTest = new ProcessaLancamentosTest(testFile)'
        And I evaluate 'testLine = procLancTest.getNextLine()'
        And I evaluate 'LancamentoTest lancTest = procLancTest.processaLinha(testLine)'
        And I evaluate 'ProcessaLancamentos p3Test = new ProcessaLancamentos(testFile)'
        And I evaluate 'Lancamento lancto = processaLinhaMethod.invoke(p3Test, new Object[] { testLine })'
        Then award 4 points

    Scenario: Check conta from processaLinha(String) call
        Given expression 'lancto != null' evaluates to <true>
        Given expression 'lancto.getConta()' evaluates to <lancTest.getConta()>
        Then award 4 points

    Scenario: Check descricao from processaLinha(String) call
        Given expression 'lancto != null' evaluates to <true>
        Given expression 'lancto.getDescricao()' evaluates to <lancTest.getDescricao()>
        Then award 2 points

    Scenario: Check valor from processaLinha(String) call
        Given expression 'lacnto != null' evaluates to <true>
        Given expression 'lancto.getValor()' evaluates to <lancTest.getValor()>
        Then award 4 points

    Scenario: Check data from processaLinha(String) call
        Given expression 'credor != null' evaluates to <true>
        Given expression 'lancto.getData()' evaluates to <lancTest.getData()>
        Then award 5 points

    Scenario: Check processaLinha(String) call for exceptions
        Given expression 'lancto != null' evaluates to <true>
        Given evaluating 'processaLinhaMethod.invoke(p3Test, new Object[] { "abc123" })' throws instance of 'java.lang.Throwable' save in <dummyEx>
        Then award 1 points

    Scenario: Implement method getNextLancamento() (20)
        Given I report 'Avaliando item 6...'
        Given class <processaLancClass> declares 'getNextLancamento()' method save in <getNextLancMethod>
        And member <getNextLancMethod> has 'private' modifier
        And method <getNextLancMethod> returns type 'utfpr.ct.dainf.if62c.avaliacao.Lancamento'
        And I set <getNextLancMethod> accessible
        And I evaluate 'ProcessaLancamentosTest pTestData = new ProcessaLancamentosTest(shortTestFile)'
        And I evaluate 'java.util.List lancListTest = pTestData.getUnorderedLancamentosList()'
        And I evaluate 'ProcessaLancamentos p4Test = new ProcessaLancamentos(shortTestFile)'
        And I evaluate 'Lancamento lancData = getNextLancMethod.invoke(p4Test, null)'
        Then award 5 points
        Given expression 'lancListTest.get(0).equalsLenient(lancData)' evaluates to <true>
        Then award 5 points
        Given I evaluate 'lancData = getNextLancMethod.invoke(p4Test, null)'
        And expression 'lancListTest.get(1).equalsLenient(lancData)' evaluates to <true>
        And I evaluate 'lancData = getNextLancMethod.invoke(p4Test, null)'
        And expression 'lancListTest.get(2).equalsLenient(lancData)' evaluates to <true>
        And I evaluate 'lancData = getNextLancMethod.invoke(p4Test, null)'
        Then award 5 points
        Given expression 'lancData == null' evaluates to <true>
        Then award 5 points


    Scenario: Implement method getLancamentos() (20)
        Given I report 'Avaliando item 7...'
        And class <processaLancClass> declares 'getLancamentos()' method save in <getLancListMethod>
        And member <getLancListMethod> has 'public' modifier
        And method <getLancListMethod> returns type 'java.util.List'
        And I evaluate 'ProcessaLancamentosTest pTestData = new ProcessaLancamentosTest(testFile)'
        And I evaluate 'java.util.List lancListTest = pTestData.getOrderedLancamentosList()'
        And I evaluate 'ProcessaLancamentos p5Test = new ProcessaLancamentos(testFile)'
        And I evaluate 'testList = p5Test.getLancamentos()'
        Then award 5 points
        Given expression 'ProcessaLancamentosTest.isSameLancamentosList(lancListTest, testList)' evaluates to <true>
        Then award 10 points
        Given I get field 'reader' value in super class of <p5Test> save in <dummyTestReader>
        Given evaluating 'dummyTestReader.readLine()' throws instance of 'java.io.IOException' save in <dummyEx>
        Then award 5 points

    Scenario: Verifica método Avaliacao3.exibeLancamentosConta. (20)
        Given I report 'Avaliando item 8...'
        Given class 'Avaliacao3' exists store class in <mainClass>
        Given I set output to <testOut>
        And I evaluate 'Avaliacao3.exibeLancamentosConta(testList, 222222)'
        And I set output to <default>
        Given <testOut> matches regex '(?s)^.*222222 01/05/2014 Cheque.*222222 01/09/2014 Debito.*$'
        Then award 20 points

    Scenario: Verifica método Avaliacao3.exibeLancamentosConta. (20)
        Given I report 'Avaliando item 9...'
        Given I evaluate 'instr = shortTestFilePath + "\n" + 999999 + "\n" + "0" + "\n"'
        Given I set output to <testOut>
        Given I set input from <instr>
        And I evaluate 'Avaliacao3.main(new String[0])'
        And I set output to <default>
        Given I set input from <default>
        And <testOut> matches regex '(?s)^.*inexistente.*$'
        Then award 20 points

    Scenario: Run program and check for input processing. (20)
        Given I report 'Avaliando item 10...'
        Given I evaluate 'instr = shortTestFilePath + "\n" + 111111 + "\n" + "abc" + "\n" + "0" + "\n"'
        Given I set output to <testOut>
        Given I set input from <instr>
        And I evaluate 'Avaliacao3.main(new String[0])'
        And I set output to <default>
        Given I set input from <default>
        And I report <testOut>
        And <testOut> matches regex '(?s)^.*111111.*informe.*$'
        Then award 20 points

    Scenario: Report final grade.
        Given I report grade formatted as 'FINAL GRADE: %.1f'
