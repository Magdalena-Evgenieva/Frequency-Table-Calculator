package com.codingStanislava;

import java.util.Objects;

public class FrequencyTableRow {
    public FrequencyTableRow(String  name, int absoluteFrequency, float relativeFrequency) {
        Name = name;
        AbsoluteFrequency = absoluteFrequency;
        RelativeFrequency = relativeFrequency;
    }
    public String Name;
    public int AbsoluteFrequency;
    public float RelativeFrequency;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequencyTableRow that = (FrequencyTableRow) o;
        return AbsoluteFrequency == that.AbsoluteFrequency && Float.compare(that.RelativeFrequency, RelativeFrequency) == 0 && Objects.equals(Name, that.Name);
    }

}
