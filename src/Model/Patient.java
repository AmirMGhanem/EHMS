package Model;

import java.util.ArrayList;

public class Patient extends Person {
    private ArrayList<Allergy> allergies = new ArrayList<Allergy>();
    private ArrayList<Medicine> medicines= new ArrayList<Medicine>();
    private ArrayList<Meal> meals = new ArrayList<Meal>();
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    public Patient() {

    }


    public void addAllergy(Allergy allergy)
    {
        allergies.add(allergy);
    }

    public void addMedicine(Medicine medicine)
    {
        medicines.add(medicine);
    }

    public void addMeal(Meal meal)
    {
        meals.add(meal);
    }

    public void addMeeting(Meeting meeting)
    {
        meetings.add(meeting);
    }


    public ArrayList<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<Allergy> allergies) {
        this.allergies = allergies;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }
}
