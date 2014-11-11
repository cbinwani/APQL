/*
 * Copyright © 2009-2014 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

// This file was generated automatically by the Snowball to Java compiler

package org.apromore.toolbox.similaritySearch.common.stemmer.ext;

import org.apromore.toolbox.similaritySearch.common.stemmer.Among;

/**
 * This class was automatically generated by a Snowball to Java compiler
 * It implements the stemming algorithm defined by a snowball script.
 */

public class romanianStemmer extends org.apromore.toolbox.similaritySearch.common.stemmer.SnowballStemmer {

    private static final long serialVersionUID = 1L;

    private final static romanianStemmer methodObject = new romanianStemmer();

    private final static Among a_0[] = {
            new Among("", -1, 3, "", methodObject),
            new Among("I", 0, 1, "", methodObject),
            new Among("U", 0, 2, "", methodObject)
    };

    private final static Among a_1[] = {
            new Among("ea", -1, 3, "", methodObject),
            new Among("a\u0163ia", -1, 7, "", methodObject),
            new Among("aua", -1, 2, "", methodObject),
            new Among("iua", -1, 4, "", methodObject),
            new Among("a\u0163ie", -1, 7, "", methodObject),
            new Among("ele", -1, 3, "", methodObject),
            new Among("ile", -1, 5, "", methodObject),
            new Among("iile", 6, 4, "", methodObject),
            new Among("iei", -1, 4, "", methodObject),
            new Among("atei", -1, 6, "", methodObject),
            new Among("ii", -1, 4, "", methodObject),
            new Among("ului", -1, 1, "", methodObject),
            new Among("ul", -1, 1, "", methodObject),
            new Among("elor", -1, 3, "", methodObject),
            new Among("ilor", -1, 4, "", methodObject),
            new Among("iilor", 14, 4, "", methodObject)
    };

    private final static Among a_2[] = {
            new Among("icala", -1, 4, "", methodObject),
            new Among("iciva", -1, 4, "", methodObject),
            new Among("ativa", -1, 5, "", methodObject),
            new Among("itiva", -1, 6, "", methodObject),
            new Among("icale", -1, 4, "", methodObject),
            new Among("a\u0163iune", -1, 5, "", methodObject),
            new Among("i\u0163iune", -1, 6, "", methodObject),
            new Among("atoare", -1, 5, "", methodObject),
            new Among("itoare", -1, 6, "", methodObject),
            new Among("\u0103toare", -1, 5, "", methodObject),
            new Among("icitate", -1, 4, "", methodObject),
            new Among("abilitate", -1, 1, "", methodObject),
            new Among("ibilitate", -1, 2, "", methodObject),
            new Among("ivitate", -1, 3, "", methodObject),
            new Among("icive", -1, 4, "", methodObject),
            new Among("ative", -1, 5, "", methodObject),
            new Among("itive", -1, 6, "", methodObject),
            new Among("icali", -1, 4, "", methodObject),
            new Among("atori", -1, 5, "", methodObject),
            new Among("icatori", 18, 4, "", methodObject),
            new Among("itori", -1, 6, "", methodObject),
            new Among("\u0103tori", -1, 5, "", methodObject),
            new Among("icitati", -1, 4, "", methodObject),
            new Among("abilitati", -1, 1, "", methodObject),
            new Among("ivitati", -1, 3, "", methodObject),
            new Among("icivi", -1, 4, "", methodObject),
            new Among("ativi", -1, 5, "", methodObject),
            new Among("itivi", -1, 6, "", methodObject),
            new Among("icit\u0103i", -1, 4, "", methodObject),
            new Among("abilit\u0103i", -1, 1, "", methodObject),
            new Among("ivit\u0103i", -1, 3, "", methodObject),
            new Among("icit\u0103\u0163i", -1, 4, "", methodObject),
            new Among("abilit\u0103\u0163i", -1, 1, "", methodObject),
            new Among("ivit\u0103\u0163i", -1, 3, "", methodObject),
            new Among("ical", -1, 4, "", methodObject),
            new Among("ator", -1, 5, "", methodObject),
            new Among("icator", 35, 4, "", methodObject),
            new Among("itor", -1, 6, "", methodObject),
            new Among("\u0103tor", -1, 5, "", methodObject),
            new Among("iciv", -1, 4, "", methodObject),
            new Among("ativ", -1, 5, "", methodObject),
            new Among("itiv", -1, 6, "", methodObject),
            new Among("ical\u0103", -1, 4, "", methodObject),
            new Among("iciv\u0103", -1, 4, "", methodObject),
            new Among("ativ\u0103", -1, 5, "", methodObject),
            new Among("itiv\u0103", -1, 6, "", methodObject)
    };

    private final static Among a_3[] = {
            new Among("ica", -1, 1, "", methodObject),
            new Among("abila", -1, 1, "", methodObject),
            new Among("ibila", -1, 1, "", methodObject),
            new Among("oasa", -1, 1, "", methodObject),
            new Among("ata", -1, 1, "", methodObject),
            new Among("ita", -1, 1, "", methodObject),
            new Among("anta", -1, 1, "", methodObject),
            new Among("ista", -1, 3, "", methodObject),
            new Among("uta", -1, 1, "", methodObject),
            new Among("iva", -1, 1, "", methodObject),
            new Among("ic", -1, 1, "", methodObject),
            new Among("ice", -1, 1, "", methodObject),
            new Among("abile", -1, 1, "", methodObject),
            new Among("ibile", -1, 1, "", methodObject),
            new Among("isme", -1, 3, "", methodObject),
            new Among("iune", -1, 2, "", methodObject),
            new Among("oase", -1, 1, "", methodObject),
            new Among("ate", -1, 1, "", methodObject),
            new Among("itate", 17, 1, "", methodObject),
            new Among("ite", -1, 1, "", methodObject),
            new Among("ante", -1, 1, "", methodObject),
            new Among("iste", -1, 3, "", methodObject),
            new Among("ute", -1, 1, "", methodObject),
            new Among("ive", -1, 1, "", methodObject),
            new Among("ici", -1, 1, "", methodObject),
            new Among("abili", -1, 1, "", methodObject),
            new Among("ibili", -1, 1, "", methodObject),
            new Among("iuni", -1, 2, "", methodObject),
            new Among("atori", -1, 1, "", methodObject),
            new Among("osi", -1, 1, "", methodObject),
            new Among("ati", -1, 1, "", methodObject),
            new Among("itati", 30, 1, "", methodObject),
            new Among("iti", -1, 1, "", methodObject),
            new Among("anti", -1, 1, "", methodObject),
            new Among("isti", -1, 3, "", methodObject),
            new Among("uti", -1, 1, "", methodObject),
            new Among("i\u015Fti", -1, 3, "", methodObject),
            new Among("ivi", -1, 1, "", methodObject),
            new Among("it\u0103i", -1, 1, "", methodObject),
            new Among("o\u015Fi", -1, 1, "", methodObject),
            new Among("it\u0103\u0163i", -1, 1, "", methodObject),
            new Among("abil", -1, 1, "", methodObject),
            new Among("ibil", -1, 1, "", methodObject),
            new Among("ism", -1, 3, "", methodObject),
            new Among("ator", -1, 1, "", methodObject),
            new Among("os", -1, 1, "", methodObject),
            new Among("at", -1, 1, "", methodObject),
            new Among("it", -1, 1, "", methodObject),
            new Among("ant", -1, 1, "", methodObject),
            new Among("ist", -1, 3, "", methodObject),
            new Among("ut", -1, 1, "", methodObject),
            new Among("iv", -1, 1, "", methodObject),
            new Among("ic\u0103", -1, 1, "", methodObject),
            new Among("abil\u0103", -1, 1, "", methodObject),
            new Among("ibil\u0103", -1, 1, "", methodObject),
            new Among("oas\u0103", -1, 1, "", methodObject),
            new Among("at\u0103", -1, 1, "", methodObject),
            new Among("it\u0103", -1, 1, "", methodObject),
            new Among("ant\u0103", -1, 1, "", methodObject),
            new Among("ist\u0103", -1, 3, "", methodObject),
            new Among("ut\u0103", -1, 1, "", methodObject),
            new Among("iv\u0103", -1, 1, "", methodObject)
    };

    private final static Among a_4[] = {
            new Among("ea", -1, 1, "", methodObject),
            new Among("ia", -1, 1, "", methodObject),
            new Among("esc", -1, 1, "", methodObject),
            new Among("\u0103sc", -1, 1, "", methodObject),
            new Among("ind", -1, 1, "", methodObject),
            new Among("\u00E2nd", -1, 1, "", methodObject),
            new Among("are", -1, 1, "", methodObject),
            new Among("ere", -1, 1, "", methodObject),
            new Among("ire", -1, 1, "", methodObject),
            new Among("\u00E2re", -1, 1, "", methodObject),
            new Among("se", -1, 2, "", methodObject),
            new Among("ase", 10, 1, "", methodObject),
            new Among("sese", 10, 2, "", methodObject),
            new Among("ise", 10, 1, "", methodObject),
            new Among("use", 10, 1, "", methodObject),
            new Among("\u00E2se", 10, 1, "", methodObject),
            new Among("e\u015Fte", -1, 1, "", methodObject),
            new Among("\u0103\u015Fte", -1, 1, "", methodObject),
            new Among("eze", -1, 1, "", methodObject),
            new Among("ai", -1, 1, "", methodObject),
            new Among("eai", 19, 1, "", methodObject),
            new Among("iai", 19, 1, "", methodObject),
            new Among("sei", -1, 2, "", methodObject),
            new Among("e\u015Fti", -1, 1, "", methodObject),
            new Among("\u0103\u015Fti", -1, 1, "", methodObject),
            new Among("ui", -1, 1, "", methodObject),
            new Among("ezi", -1, 1, "", methodObject),
            new Among("\u00E2i", -1, 1, "", methodObject),
            new Among("a\u015Fi", -1, 1, "", methodObject),
            new Among("se\u015Fi", -1, 2, "", methodObject),
            new Among("ase\u015Fi", 29, 1, "", methodObject),
            new Among("sese\u015Fi", 29, 2, "", methodObject),
            new Among("ise\u015Fi", 29, 1, "", methodObject),
            new Among("use\u015Fi", 29, 1, "", methodObject),
            new Among("\u00E2se\u015Fi", 29, 1, "", methodObject),
            new Among("i\u015Fi", -1, 1, "", methodObject),
            new Among("u\u015Fi", -1, 1, "", methodObject),
            new Among("\u00E2\u015Fi", -1, 1, "", methodObject),
            new Among("a\u0163i", -1, 2, "", methodObject),
            new Among("ea\u0163i", 38, 1, "", methodObject),
            new Among("ia\u0163i", 38, 1, "", methodObject),
            new Among("e\u0163i", -1, 2, "", methodObject),
            new Among("i\u0163i", -1, 2, "", methodObject),
            new Among("\u00E2\u0163i", -1, 2, "", methodObject),
            new Among("ar\u0103\u0163i", -1, 1, "", methodObject),
            new Among("ser\u0103\u0163i", -1, 2, "", methodObject),
            new Among("aser\u0103\u0163i", 45, 1, "", methodObject),
            new Among("seser\u0103\u0163i", 45, 2, "", methodObject),
            new Among("iser\u0103\u0163i", 45, 1, "", methodObject),
            new Among("user\u0103\u0163i", 45, 1, "", methodObject),
            new Among("\u00E2ser\u0103\u0163i", 45, 1, "", methodObject),
            new Among("ir\u0103\u0163i", -1, 1, "", methodObject),
            new Among("ur\u0103\u0163i", -1, 1, "", methodObject),
            new Among("\u00E2r\u0103\u0163i", -1, 1, "", methodObject),
            new Among("am", -1, 1, "", methodObject),
            new Among("eam", 54, 1, "", methodObject),
            new Among("iam", 54, 1, "", methodObject),
            new Among("em", -1, 2, "", methodObject),
            new Among("asem", 57, 1, "", methodObject),
            new Among("sesem", 57, 2, "", methodObject),
            new Among("isem", 57, 1, "", methodObject),
            new Among("usem", 57, 1, "", methodObject),
            new Among("\u00E2sem", 57, 1, "", methodObject),
            new Among("im", -1, 2, "", methodObject),
            new Among("\u00E2m", -1, 2, "", methodObject),
            new Among("\u0103m", -1, 2, "", methodObject),
            new Among("ar\u0103m", 65, 1, "", methodObject),
            new Among("ser\u0103m", 65, 2, "", methodObject),
            new Among("aser\u0103m", 67, 1, "", methodObject),
            new Among("seser\u0103m", 67, 2, "", methodObject),
            new Among("iser\u0103m", 67, 1, "", methodObject),
            new Among("user\u0103m", 67, 1, "", methodObject),
            new Among("\u00E2ser\u0103m", 67, 1, "", methodObject),
            new Among("ir\u0103m", 65, 1, "", methodObject),
            new Among("ur\u0103m", 65, 1, "", methodObject),
            new Among("\u00E2r\u0103m", 65, 1, "", methodObject),
            new Among("au", -1, 1, "", methodObject),
            new Among("eau", 76, 1, "", methodObject),
            new Among("iau", 76, 1, "", methodObject),
            new Among("indu", -1, 1, "", methodObject),
            new Among("\u00E2ndu", -1, 1, "", methodObject),
            new Among("ez", -1, 1, "", methodObject),
            new Among("easc\u0103", -1, 1, "", methodObject),
            new Among("ar\u0103", -1, 1, "", methodObject),
            new Among("ser\u0103", -1, 2, "", methodObject),
            new Among("aser\u0103", 84, 1, "", methodObject),
            new Among("seser\u0103", 84, 2, "", methodObject),
            new Among("iser\u0103", 84, 1, "", methodObject),
            new Among("user\u0103", 84, 1, "", methodObject),
            new Among("\u00E2ser\u0103", 84, 1, "", methodObject),
            new Among("ir\u0103", -1, 1, "", methodObject),
            new Among("ur\u0103", -1, 1, "", methodObject),
            new Among("\u00E2r\u0103", -1, 1, "", methodObject),
            new Among("eaz\u0103", -1, 1, "", methodObject)
    };

    private final static Among a_5[] = {
            new Among("a", -1, 1, "", methodObject),
            new Among("e", -1, 1, "", methodObject),
            new Among("ie", 1, 1, "", methodObject),
            new Among("i", -1, 1, "", methodObject),
            new Among("\u0103", -1, 1, "", methodObject)
    };

    private static final char g_v[] = {17, 65, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 32, 0, 0, 4};

    private boolean B_standard_suffix_removed;
    private int I_p2;
    private int I_p1;
    private int I_pV;

    private void copy_from(romanianStemmer other) {
        B_standard_suffix_removed = other.B_standard_suffix_removed;
        I_p2 = other.I_p2;
        I_p1 = other.I_p1;
        I_pV = other.I_pV;
        super.copy_from(other);
    }

    private boolean r_prelude() {
        int v_1;
        int v_2;
        int v_3;
        // (, line 31
        // repeat, line 32
        replab0:
        while (true) {
            v_1 = cursor;
            lab1:
            do {
                // goto, line 32
                golab2:
                while (true) {
                    v_2 = cursor;
                    lab3:
                    do {
                        // (, line 32
                        if (!(in_grouping(g_v, 97, 259))) {
                            break lab3;
                        }
                        // [, line 33
                        bra = cursor;
                        // or, line 33
                        lab4:
                        do {
                            v_3 = cursor;
                            lab5:
                            do {
                                // (, line 33
                                // literal, line 33
                                if (!(eq_s(1, "u"))) {
                                    break lab5;
                                }
                                // ], line 33
                                ket = cursor;
                                if (!(in_grouping(g_v, 97, 259))) {
                                    break lab5;
                                }
                                // <-, line 33
                                slice_from("U");
                                break lab4;
                            } while (false);
                            cursor = v_3;
                            // (, line 34
                            // literal, line 34
                            if (!(eq_s(1, "i"))) {
                                break lab3;
                            }
                            // ], line 34
                            ket = cursor;
                            if (!(in_grouping(g_v, 97, 259))) {
                                break lab3;
                            }
                            // <-, line 34
                            slice_from("I");
                        } while (false);
                        cursor = v_2;
                        break golab2;
                    } while (false);
                    cursor = v_2;
                    if (cursor >= limit) {
                        break lab1;
                    }
                    cursor++;
                }
                continue replab0;
            } while (false);
            cursor = v_1;
            break replab0;
        }
        return true;
    }

    private boolean r_mark_regions() {
        int v_1;
        int v_2;
        int v_3;
        int v_6;
        int v_8;
        // (, line 38
        I_pV = limit;
        I_p1 = limit;
        I_p2 = limit;
        // do, line 44
        v_1 = cursor;
        lab0:
        do {
            // (, line 44
            // or, line 46
            lab1:
            do {
                v_2 = cursor;
                lab2:
                do {
                    // (, line 45
                    if (!(in_grouping(g_v, 97, 259))) {
                        break lab2;
                    }
                    // or, line 45
                    lab3:
                    do {
                        v_3 = cursor;
                        lab4:
                        do {
                            // (, line 45
                            if (!(out_grouping(g_v, 97, 259))) {
                                break lab4;
                            }
                            // gopast, line 45
                            golab5:
                            while (true) {
                                lab6:
                                do {
                                    if (!(in_grouping(g_v, 97, 259))) {
                                        break lab6;
                                    }
                                    break golab5;
                                } while (false);
                                if (cursor >= limit) {
                                    break lab4;
                                }
                                cursor++;
                            }
                            break lab3;
                        } while (false);
                        cursor = v_3;
                        // (, line 45
                        if (!(in_grouping(g_v, 97, 259))) {
                            break lab2;
                        }
                        // gopast, line 45
                        golab7:
                        while (true) {
                            lab8:
                            do {
                                if (!(out_grouping(g_v, 97, 259))) {
                                    break lab8;
                                }
                                break golab7;
                            } while (false);
                            if (cursor >= limit) {
                                break lab2;
                            }
                            cursor++;
                        }
                    } while (false);
                    break lab1;
                } while (false);
                cursor = v_2;
                // (, line 47
                if (!(out_grouping(g_v, 97, 259))) {
                    break lab0;
                }
                // or, line 47
                lab9:
                do {
                    v_6 = cursor;
                    lab10:
                    do {
                        // (, line 47
                        if (!(out_grouping(g_v, 97, 259))) {
                            break lab10;
                        }
                        // gopast, line 47
                        golab11:
                        while (true) {
                            lab12:
                            do {
                                if (!(in_grouping(g_v, 97, 259))) {
                                    break lab12;
                                }
                                break golab11;
                            } while (false);
                            if (cursor >= limit) {
                                break lab10;
                            }
                            cursor++;
                        }
                        break lab9;
                    } while (false);
                    cursor = v_6;
                    // (, line 47
                    if (!(in_grouping(g_v, 97, 259))) {
                        break lab0;
                    }
                    // next, line 47
                    if (cursor >= limit) {
                        break lab0;
                    }
                    cursor++;
                } while (false);
            } while (false);
            // setmark pV, line 48
            I_pV = cursor;
        } while (false);
        cursor = v_1;
        // do, line 50
        v_8 = cursor;
        lab13:
        do {
            // (, line 50
            // gopast, line 51
            golab14:
            while (true) {
                lab15:
                do {
                    if (!(in_grouping(g_v, 97, 259))) {
                        break lab15;
                    }
                    break golab14;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // gopast, line 51
            golab16:
            while (true) {
                lab17:
                do {
                    if (!(out_grouping(g_v, 97, 259))) {
                        break lab17;
                    }
                    break golab16;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // setmark p1, line 51
            I_p1 = cursor;
            // gopast, line 52
            golab18:
            while (true) {
                lab19:
                do {
                    if (!(in_grouping(g_v, 97, 259))) {
                        break lab19;
                    }
                    break golab18;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // gopast, line 52
            golab20:
            while (true) {
                lab21:
                do {
                    if (!(out_grouping(g_v, 97, 259))) {
                        break lab21;
                    }
                    break golab20;
                } while (false);
                if (cursor >= limit) {
                    break lab13;
                }
                cursor++;
            }
            // setmark p2, line 52
            I_p2 = cursor;
        } while (false);
        cursor = v_8;
        return true;
    }

    private boolean r_postlude() {
        int among_var;
        int v_1;
        // repeat, line 56
        replab0:
        while (true) {
            v_1 = cursor;
            lab1:
            do {
                // (, line 56
                // [, line 58
                bra = cursor;
                // substring, line 58
                among_var = find_among(a_0, 3);
                if (among_var == 0) {
                    break lab1;
                }
                // ], line 58
                ket = cursor;
                switch (among_var) {
                    case 0:
                        break lab1;
                    case 1:
                        // (, line 59
                        // <-, line 59
                        slice_from("i");
                        break;
                    case 2:
                        // (, line 60
                        // <-, line 60
                        slice_from("u");
                        break;
                    case 3:
                        // (, line 61
                        // next, line 61
                        if (cursor >= limit) {
                            break lab1;
                        }
                        cursor++;
                        break;
                }
                continue replab0;
            } while (false);
            cursor = v_1;
            break replab0;
        }
        return true;
    }

    private boolean r_RV() {
        if (!(I_pV <= cursor)) {
            return false;
        }
        return true;
    }

    private boolean r_R1() {
        if (!(I_p1 <= cursor)) {
            return false;
        }
        return true;
    }

    private boolean r_R2() {
        if (!(I_p2 <= cursor)) {
            return false;
        }
        return true;
    }

    private boolean r_step_0() {
        int among_var;
        int v_1;
        // (, line 72
        // [, line 73
        ket = cursor;
        // substring, line 73
        among_var = find_among_b(a_1, 16);
        if (among_var == 0) {
            return false;
        }
        // ], line 73
        bra = cursor;
        // call R1, line 73
        if (!r_R1()) {
            return false;
        }
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 75
                // delete, line 75
                slice_del();
                break;
            case 2:
                // (, line 77
                // <-, line 77
                slice_from("a");
                break;
            case 3:
                // (, line 79
                // <-, line 79
                slice_from("e");
                break;
            case 4:
                // (, line 81
                // <-, line 81
                slice_from("i");
                break;
            case 5:
                // (, line 83
                // not, line 83
            {
                v_1 = limit - cursor;
                lab0:
                do {
                    // literal, line 83
                    if (!(eq_s_b(2, "ab"))) {
                        break lab0;
                    }
                    return false;
                } while (false);
                cursor = limit - v_1;
            }
            // <-, line 83
            slice_from("i");
            break;
            case 6:
                // (, line 85
                // <-, line 85
                slice_from("at");
                break;
            case 7:
                // (, line 87
                // <-, line 87
                slice_from("a\u0163i");
                break;
        }
        return true;
    }

    private boolean r_combo_suffix() {
        int among_var;
        int v_1;
        // test, line 91
        v_1 = limit - cursor;
        // (, line 91
        // [, line 92
        ket = cursor;
        // substring, line 92
        among_var = find_among_b(a_2, 46);
        if (among_var == 0) {
            return false;
        }
        // ], line 92
        bra = cursor;
        // call R1, line 92
        if (!r_R1()) {
            return false;
        }
        // (, line 92
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 100
                // <-, line 101
                slice_from("abil");
                break;
            case 2:
                // (, line 103
                // <-, line 104
                slice_from("ibil");
                break;
            case 3:
                // (, line 106
                // <-, line 107
                slice_from("iv");
                break;
            case 4:
                // (, line 112
                // <-, line 113
                slice_from("ic");
                break;
            case 5:
                // (, line 117
                // <-, line 118
                slice_from("at");
                break;
            case 6:
                // (, line 121
                // <-, line 122
                slice_from("it");
                break;
        }
        // set standard_suffix_removed, line 125
        B_standard_suffix_removed = true;
        cursor = limit - v_1;
        return true;
    }

    private boolean r_standard_suffix() {
        int among_var;
        int v_1;
        // (, line 129
        // unset standard_suffix_removed, line 130
        B_standard_suffix_removed = false;
        // repeat, line 131
        replab0:
        while (true) {
            v_1 = limit - cursor;
            lab1:
            do {
                // call combo_suffix, line 131
                if (!r_combo_suffix()) {
                    break lab1;
                }
                continue replab0;
            } while (false);
            cursor = limit - v_1;
            break replab0;
        }
        // [, line 132
        ket = cursor;
        // substring, line 132
        among_var = find_among_b(a_3, 62);
        if (among_var == 0) {
            return false;
        }
        // ], line 132
        bra = cursor;
        // call R2, line 132
        if (!r_R2()) {
            return false;
        }
        // (, line 132
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 148
                // delete, line 149
                slice_del();
                break;
            case 2:
                // (, line 151
                // literal, line 152
                if (!(eq_s_b(1, "\u0163"))) {
                    return false;
                }
                // ], line 152
                bra = cursor;
                // <-, line 152
                slice_from("t");
                break;
            case 3:
                // (, line 155
                // <-, line 156
                slice_from("ist");
                break;
        }
        // set standard_suffix_removed, line 160
        B_standard_suffix_removed = true;
        return true;
    }

    private boolean r_verb_suffix() {
        int among_var;
        int v_1;
        int v_2;
        int v_3;
        // setlimit, line 164
        v_1 = limit - cursor;
        // tomark, line 164
        if (cursor < I_pV) {
            return false;
        }
        cursor = I_pV;
        v_2 = limit_backward;
        limit_backward = cursor;
        cursor = limit - v_1;
        // (, line 164
        // [, line 165
        ket = cursor;
        // substring, line 165
        among_var = find_among_b(a_4, 94);
        if (among_var == 0) {
            limit_backward = v_2;
            return false;
        }
        // ], line 165
        bra = cursor;
        switch (among_var) {
            case 0:
                limit_backward = v_2;
                return false;
            case 1:
                // (, line 200
                // or, line 200
                lab0:
                do {
                    v_3 = limit - cursor;
                    lab1:
                    do {
                        if (!(out_grouping_b(g_v, 97, 259))) {
                            break lab1;
                        }
                        break lab0;
                    } while (false);
                    cursor = limit - v_3;
                    // literal, line 200
                    if (!(eq_s_b(1, "u"))) {
                        limit_backward = v_2;
                        return false;
                    }
                } while (false);
                // delete, line 200
                slice_del();
                break;
            case 2:
                // (, line 214
                // delete, line 214
                slice_del();
                break;
        }
        limit_backward = v_2;
        return true;
    }

    private boolean r_vowel_suffix() {
        int among_var;
        // (, line 218
        // [, line 219
        ket = cursor;
        // substring, line 219
        among_var = find_among_b(a_5, 5);
        if (among_var == 0) {
            return false;
        }
        // ], line 219
        bra = cursor;
        // call RV, line 219
        if (!r_RV()) {
            return false;
        }
        switch (among_var) {
            case 0:
                return false;
            case 1:
                // (, line 220
                // delete, line 220
                slice_del();
                break;
        }
        return true;
    }

    public boolean stem() {
        int v_1;
        int v_2;
        int v_3;
        int v_4;
        int v_5;
        int v_6;
        int v_7;
        int v_8;
        // (, line 225
        // do, line 226
        v_1 = cursor;
        lab0:
        do {
            // call prelude, line 226
            if (!r_prelude()) {
                break lab0;
            }
        } while (false);
        cursor = v_1;
        // do, line 227
        v_2 = cursor;
        lab1:
        do {
            // call mark_regions, line 227
            if (!r_mark_regions()) {
                break lab1;
            }
        } while (false);
        cursor = v_2;
        // backwards, line 228
        limit_backward = cursor;
        cursor = limit;
        // (, line 228
        // do, line 229
        v_3 = limit - cursor;
        lab2:
        do {
            // call step_0, line 229
            if (!r_step_0()) {
                break lab2;
            }
        } while (false);
        cursor = limit - v_3;
        // do, line 230
        v_4 = limit - cursor;
        lab3:
        do {
            // call standard_suffix, line 230
            if (!r_standard_suffix()) {
                break lab3;
            }
        } while (false);
        cursor = limit - v_4;
        // do, line 231
        v_5 = limit - cursor;
        lab4:
        do {
            // (, line 231
            // or, line 231
            lab5:
            do {
                v_6 = limit - cursor;
                lab6:
                do {
                    // Boolean test standard_suffix_removed, line 231
                    if (!(B_standard_suffix_removed)) {
                        break lab6;
                    }
                    break lab5;
                } while (false);
                cursor = limit - v_6;
                // call verb_suffix, line 231
                if (!r_verb_suffix()) {
                    break lab4;
                }
            } while (false);
        } while (false);
        cursor = limit - v_5;
        // do, line 232
        v_7 = limit - cursor;
        lab7:
        do {
            // call vowel_suffix, line 232
            if (!r_vowel_suffix()) {
                break lab7;
            }
        } while (false);
        cursor = limit - v_7;
        cursor = limit_backward;                    // do, line 234
        v_8 = cursor;
        lab8:
        do {
            // call postlude, line 234
            if (!r_postlude()) {
                break lab8;
            }
        } while (false);
        cursor = v_8;
        return true;
    }

    public boolean equals(Object o) {
        return o instanceof romanianStemmer;
    }

    public int hashCode() {
        return romanianStemmer.class.getName().hashCode();
    }


}
