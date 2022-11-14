package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(name="car_series")
   private int series;

   @Column(name = "model")
   private String model;

   public Car() {

   }

   public Car(int series, String model) {
      this.series = series;
      this.model = model;
   }

   public Car(int series, String model, User user) {
      this(series, model);
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
