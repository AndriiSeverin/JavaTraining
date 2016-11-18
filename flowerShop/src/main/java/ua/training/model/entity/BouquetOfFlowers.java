package ua.training.model.entity;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by andrii on 18.11.16.
 */
public class BouquetOfFlowers implements Salable {

    /**
     *
     */
    private TreeMap<SalableFlower, Integer> flowers;

    /**
     *
     */
    private ArrayList<SalableFlower> listOfFlowers;

    /**
     * The Date of Supply
     */
    private LocalDate dateOfSupply;

    public BouquetOfFlowers() {
        flowers = new TreeMap<>();
        dateOfSupply = LocalDate.now();
        listOfFlowers = new ArrayList<>();
    }

//    public SortedMap<Double, SalableFlower> sortByFreshness(){
//        return null;
//    }

    /**
     * Add flower to bouquet of flowers
     * @param salableFlower flower that you want to add
     */
    public void addFlower(SalableFlower salableFlower) {
        if (salableFlower == null) {
            return;
        }
        listOfFlowers.add(salableFlower);
        if (flowers.containsKey(salableFlower)) {
            flowers.replace(
                    salableFlower,
                    flowers.get(salableFlower) + 1);
        } else {
            flowers.put(salableFlower, 1);
        }
    }

    /**
     * Remove flower from bouquet of flowers
     * @param flower
     * @return
     */
    public boolean removeFlower(SalableFlower flower) {
        if (flower == null) {
            return false;
        }
        listOfFlowers.remove(flower);
        if (flowers.containsKey(flower)) {
            if (flowers.get(flower) > 1) {
                flowers.replace(
                        flower,
                        flowers.get(flower) - 1
                );
            } else {
                flowers.remove(flower);
            }
            return true;
        }
        return false;
    }

    public void sortByDateOfSupply(){
        Comparator comparatorByDateOfSupply = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (((SalableFlower)o1).getDateOfSupply().getMonthValue() >
                        ((SalableFlower)o2).getDateOfSupply().getMonthValue()) {
                    return 1;
                } else if (((SalableFlower)o1).getDateOfSupply().getMonthValue() ==
                        ((SalableFlower)o2).getDateOfSupply().getMonthValue() &&
                        ((SalableFlower)o1).getDateOfSupply().getDayOfMonth() >
                                ((SalableFlower)o2).getDateOfSupply().getDayOfMonth()) {
                    return 1;
                } else {
                        return -1;
                }
            }
        };
        Collections.sort(listOfFlowers, comparatorByDateOfSupply);
    }

    public ArrayList<SalableFlower> getFlowersByLengthOfPlantStemRange(double left, double right) {
        ArrayList<SalableFlower> result = new ArrayList<>();
        for(SalableFlower f : listOfFlowers) {
            double length = f.getFlower().getLengthOfPlantStem();
            if (length > left && length < right) {
                result.add(f);
            }
        }
        return result;//flowers.subMap(left, right);
    }

//    public SortedMap<Double, TreeMap<SalableFlower, Integer>> getFlowersByLengthOfPlantStemRange(
//            double left, double right) {
//        TreeMap treeMap = new TreeMap<Double, TreeMap<SalableFlower, Integer>>();
//
//        return null;//flowers.subMap(left, right);
//    }

    /**
     *
     * @return
     */
    @Override
    public double getPrice() {
        double price = 0;

        for (SalableFlower flower : listOfFlowers) {
            price += flower.getPrice();
        }

//        for(Map.Entry<SalableFlower, Integer> flower : flowers.entrySet()) {
//            price += flower.getKey().getPrice();
//        }
        return price;
    }

    public ArrayList<SalableFlower> getListOfFlowers() {
        return listOfFlowers;
    }

    @Override
    public LocalDate getDateOfSupply() {
        return null;
    }

    @Override
    public String toString() {
        return "BouquetOfFlowers{" +
                "flowers=" + flowers +
                '}';
     }
}