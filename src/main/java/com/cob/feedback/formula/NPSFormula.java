package com.cob.feedback.formula;

public class NPSFormula {

    public static long calculate(long total, int vGood, int vBad) {
        /*
            How NPS Works
                On a scale of 0 to 10, how likely are you to recommend our service to a friend or colleague?
                Then they are grouped as:
                    Score | Category | Meaning
                    9–10 | Promoters | Very satisfied, loyal
                    7–8 | Passives | Satisfied but unenthusiastic
                    0–6 | Detractors | Unhappy, may discourage others
             NPS=%Promoters− %Detractors

          Based on VGood , Good and VBad customer satisfaction  scale
                  Your Label | Mapped NPS Score | NPS Category
                   Vgood     | 9–10             | Promoter
                   Good      | 7–8              | Passive
                   Vbad      | 0–6              | Detractor
         */
        if (total == 0) return 0;
        double promoters = (vGood * 100.0) / total;
        double detractors = (vBad * 100.0) / total;
        double nps = promoters - detractors;

        return (int) Math.round(nps);
    }
}
