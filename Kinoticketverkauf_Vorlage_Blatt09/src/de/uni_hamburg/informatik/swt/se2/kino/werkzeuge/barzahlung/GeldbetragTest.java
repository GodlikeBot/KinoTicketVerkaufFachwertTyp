package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GeldbetragTest
{
    private String _wertEins;
    private int _wertEinsEuro;
    private int _wertEinsCent;
    private String _wertZwei;
    private int _wertZweiEuro;
    private int _wertZweiCent;
    private int _multiplikator;

    @Before
    public void createTestData()
    {
        _wertEinsEuro = 59;
        _wertEinsCent = 36;
        _wertEins = _wertEinsEuro + "," + _wertEinsCent;

        _wertZweiEuro = 123;
        _wertZweiCent = 96;
        _wertZwei = _wertZweiEuro + "," + _wertZweiCent;

        _multiplikator = 5;
    }

    @Test
    public void testeGeldbetragKonstruktoren()
    {
        Geldbetrag betragEins = new Geldbetrag(_wertEins);
        Geldbetrag betragZwei = new Geldbetrag(_wertZweiEuro, _wertZweiCent);

        assertEquals(_wertEinsEuro, betragEins.getEuroBetrag());
        assertEquals(_wertEinsCent, betragEins.getCentBetrag());
        assertEquals(_wertZweiEuro, betragZwei.getEuroBetrag());
        assertEquals(_wertZweiCent, betragZwei.getCentBetrag());
    }

    @Test
    public void testeAddiereGeldbetraege()
    {
        Geldbetrag grundBetrag = new Geldbetrag(_wertEins);
        Geldbetrag zuAddierenderBetrag = new Geldbetrag(_wertZwei);
        grundBetrag = grundBetrag.addiere(zuAddierenderBetrag);

        int centGesamt = _wertEinsCent + _wertZweiCent;
        int euroGesamt = _wertEinsEuro + _wertZweiEuro;
        int zuAddierendeEuro = centGesamt / 100;
        centGesamt -= zuAddierendeEuro * 100;
        euroGesamt += zuAddierendeEuro;

        assertEquals(euroGesamt, grundBetrag.getEuroBetrag());
        assertEquals(centGesamt, grundBetrag.getCentBetrag());
    }

    @Test
    public void testeSubtrahiereGeldbetraege()
    {
        Geldbetrag grundBetrag = new Geldbetrag(_wertEins);
        Geldbetrag zuSubtrahierenderBetrag = new Geldbetrag(_wertZwei);
        grundBetrag = grundBetrag.subtrahiere(zuSubtrahierenderBetrag);

        int centGesamt = _wertEinsCent - _wertZweiCent;
        int euroGesamt = _wertEinsEuro - _wertZweiEuro;

        int zuSubtrahierendeEuro = 0;
        while (centGesamt < 0)
        {
            zuSubtrahierendeEuro++;
            centGesamt += 100;
        }
        euroGesamt -= zuSubtrahierendeEuro;

        assertEquals(euroGesamt, grundBetrag.getEuroBetrag());
        assertEquals(centGesamt, grundBetrag.getCentBetrag());
    }

    @Test
    public void testeMultipliziere()
    {
        Geldbetrag geldbetrag = new Geldbetrag(_wertEins);
        geldbetrag = geldbetrag.multipliziere(_multiplikator);

        int expectedEuroBetrag = _wertEinsEuro * _multiplikator;
        int expectedCentBetrag = _wertEinsCent * _multiplikator;

        int zuAddierendeEuro = expectedCentBetrag / 100;
        expectedCentBetrag -= zuAddierendeEuro * 100;
        expectedEuroBetrag += zuAddierendeEuro;

        assertEquals(expectedEuroBetrag, geldbetrag.getEuroBetrag());
        assertEquals(expectedCentBetrag, geldbetrag.getCentBetrag());
    }

    @Test
    public void testeGibGeldbetragDarstellung()
    {
        Geldbetrag geldbetrag = new Geldbetrag(_wertEins);
        String stringDarstellung = geldbetrag.gibGeldbetragDarstellung();
        assertEquals(_wertEinsEuro + "," + _wertEinsCent, stringDarstellung);
    }
}