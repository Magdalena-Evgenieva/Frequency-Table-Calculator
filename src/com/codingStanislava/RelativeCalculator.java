package com.codingStanislava;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class RelativeCalculator {
    public static SortedMap<String, FrequencyTableRow> CalculateRelative(SortedMap<String, FrequencyTableRow> info){
        Set s = info.entrySet();
        Iterator i = s.iterator();
        int sum = 0;

        while (i.hasNext()) {
            Map.Entry m = (Map.Entry)i.next();
            FrequencyTableRow temp =  (FrequencyTableRow)m.getValue();
            sum += temp.AbsoluteFrequency;

        }
        i = s.iterator();

        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();
            String key = (String)m.getKey();

            FrequencyTableRow temp_value = (FrequencyTableRow) m.getValue();

            float relative = ((float) temp_value.AbsoluteFrequency / (float) sum);
            FrequencyTableRow date_temp = info.get(key);
            date_temp.RelativeFrequency = relative;
            //date_temp.AbsoluteFrequency /= 2;

            // Printing value for the corresponding key
            info.replace(key,date_temp);

        }
        return info;

    }
}

