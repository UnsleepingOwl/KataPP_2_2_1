package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

   @Id
   @Column(name="car_series")
   private int series;

   @Column(name = "model")
   private String model;

   @OneToOne(
           fetch = FetchType.LAZY,
           mappedBy = "car",
           orphanRemoval = true
   )
   private User user;

   public Car() {

   }

   public Car(int series, String model) {
      this.series = series;
      this.model = model;
   }

   public Car(int series, String model, User user) {
      this(series, model);
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User owner) {
      this.user = owner;
   }

   public int getSeries() {
      return series;
   }

   public void setSeries(int series) {
      this.series = series;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   @Override
   public String toString() {
      return "Series = " + series + " | " + "Model = '" + model + '\'';
   }
}
