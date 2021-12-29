package pl.edu.pw.ee;

public class ChristmasBonus {

    public int findMaxLength(int[] schedule, int changes) {
        validateSchedule(schedule);
        validateChanges(changes);
        int biggest = 0;
        int changesTym = changes;
        int i = 0;
        int j = i + 1;
        while (i < schedule.length) {
            if (j == schedule.length) {
                biggest = max(biggest, j - i + changesTym);
                changesTym = changes;
                i++;
                j = i + 1;
            } else if (schedule[i] == schedule[j]) {
                j++;
            } else {
                if (changesTym > 0) {
                    j++;
                    changesTym--;
                } else {
                    biggest = max(biggest, j - i);
                    changesTym = changes;
                    i++;
                    j = i + 1;
                }
            }
        }
        if (biggest > schedule.length) {
            return schedule.length;
        } else {
            return biggest;
        }
    }

    private int max(int first, int second) {
        if (first > second) {
            return first;
        } else {
            return second;
        }
    }

    private void validateSchedule(int[] schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Table is null");
        }
        if (schedule.length > 99999) {
            throw new IllegalArgumentException("To long table");
        }
        for(int i = 0; i < schedule.length; i++){
            if(schedule[i] < 1 || schedule[i] > 99999){
                throw new IllegalArgumentException("Bad table value");
            }
        }
    }

    private void validateChanges(int changes) {
        if (changes > 99999) {
            throw new IllegalArgumentException("Too many changes");
        }
        if (changes < 0) {
            throw new IllegalArgumentException("Changes connot be null");
        }
    }
}