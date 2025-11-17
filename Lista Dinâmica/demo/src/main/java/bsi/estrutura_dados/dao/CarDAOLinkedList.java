package bsi.estrutura_dados.dao;

import bsi.estrutura_dados.dao.repository.LinkedList;
import bsi.estrutura_dados.dao.repository.Listable;
import bsi.estrutura_dados.dao.model.Car;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CarDAOLinkedList implements CarDAO {
    private Listable<Car> cars = new LinkedList<>(20);
    private static final int MAX_CAPACITY = 20;

    // Operações básicas CRUD
    @Override
    public void addCar(Car car) {
        cars.append(car);
    }

    @Override
    public Car getCar(String plateLicense) {
        return getCarByLicensePlate(plateLicense);
    }

    @Override
    public Car[] getAllCars() {
        return cars.selectAll();
    }

    @Override
    public void updateCar(Car newCar) {
        Car[] allCars = cars.selectAll();
        for (int i = 0; i < allCars.length; i++) {
            if (allCars[i].getLicensePlate().equals(newCar.getLicensePlate())) {
                cars.update(newCar, i);
                return;
            }
        }
    }

    @Override
    public Car deleteCar(String plateLicense) {
        Car[] allCars = cars.selectAll();
        for (int i = 0; i < allCars.length; i++) {
            if (allCars[i].getLicensePlate().equals(plateLicense)) {
                return cars.delete(i);
            }
        }
        return null;
    }

    // Operações de consulta específicas para carros
    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        Car[] allCars = cars.selectAll();
        for (Car car : allCars) {
            if (car.getLicensePlate() != null && car.getLicensePlate().equals(licensePlate)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Car[] getCarsByMark(String mark) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            if (car.getMark() != null && car.getMark().equalsIgnoreCase(mark)) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    @Override
    public Car[] getCarsByModel(String model) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            if (car.getModel() != null && car.getModel().equalsIgnoreCase(model)) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    @Override
    public Car[] getCarsByColor(String color) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    @Override
    public Car[] getCarsByOwner(String owner) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            if (car.getArrived() != null &&
                !car.getArrived().isBefore(initialMoment) &&
                !car.getArrived().isAfter(finalMoment)) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    // Operações de análise e estatísticas
    @Override
    public Car getCarByNewestArrival() {
        Car[] allCars = cars.selectAll();
        Car resultCar = null;

        for (Car car : allCars) {
            if (car.getArrived() != null) {
                if (resultCar == null || car.getArrived().isAfter(resultCar.getArrived())) {
                    resultCar = car;
                }
            }
        }

        return resultCar;
    }

    @Override
    public Car getCarByOldestArrival() {
        Car[] allCars = cars.selectAll();
        Car resultCar = null;

        for (Car car : allCars) {
            if (car.getArrived() != null) {
                if (resultCar == null || car.getArrived().isBefore(resultCar.getArrived())) {
                    resultCar = car;
                }
            }
        }

        return resultCar;
    }

    // Operações de relatório e estatísticas
    @Override
    public String printCars() {
        return cars.print();
    }

    @Override
    public int getTotalCars() {
        return cars.size();
    }

    @Override
    public String getMostPopularMark() {
        String mostPopularMark = "";
        int maxCount = 0;

        Car[] allCars = cars.selectAll();

        for (Car car : allCars) {
            if (car.getMark() != null) {
                int count = 0;
                for (Car compareCar : allCars) {
                    if (compareCar.getMark() != null && 
                        compareCar.getMark().equalsIgnoreCase(car.getMark())) {
                        count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    mostPopularMark = car.getMark();
                }
            }
        }

        return mostPopularMark;
    }

    @Override
    public String getMostPopularModel() {
        String mostPopularModel = "";
        int maxCount = 0;

        Car[] allCars = cars.selectAll();

        for (Car car : allCars) {
            if (car.getModel() != null) {
                int count = 0;
                for (Car compareCar : allCars) {
                    if (compareCar.getModel() != null && 
                        compareCar.getModel().equalsIgnoreCase(car.getModel())) {
                        count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    mostPopularModel = car.getModel();
                }
            }
        }

        return mostPopularModel;
    }

    @Override
    public String getMostPopularColor() {
        String mostPopularColor = "";
        int maxCount = 0;

        Car[] allCars = cars.selectAll();

        for (Car car : allCars) {
            if (car.getColor() != null) {
                int count = 0;
                for (Car compareCar : allCars) {
                    if (compareCar.getColor() != null && 
                        compareCar.getColor().equalsIgnoreCase(car.getColor())) {
                        count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    mostPopularColor = car.getColor();
                }
            }
        }

        return mostPopularColor;
    }

    @Override
    public long getParkingDuration(String plateLicense) {
        Car car = getCarByLicensePlate(plateLicense);
        if (car != null && car.getArrived() != null) {
            return ChronoUnit.HOURS.between(car.getArrived(), LocalDateTime.now());
        }
        return -1;
    }

    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            long duration = getParkingDuration(car.getLicensePlate());
            if (duration >= minHours && duration <= maxHours) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    // Operações de gerenciamento
    @Override
    public boolean isCarInPlaced(String plateLicense) {
        return getCarByLicensePlate(plateLicense) != null;
    }

    @Override
    public void clearAllCars() {
        cars.clear();
    }

    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        Car[] allCars = cars.selectAll();
        cars.clear();

        for (Car car : allCars) {
            if (car.getArrived() == null || !car.getArrived().isBefore(date)) {
                cars.append(car);
            }
        }
    }

    @Override
    public void removeCarsByOwner(String owner) {
        Car[] allCars = cars.selectAll();
        cars.clear();

        for (Car car : allCars) {
            if (car.getOwnerName() == null || !car.getOwnerName().equalsIgnoreCase(owner)) {
                cars.append(car);
            }
        }
    }

    @Override
    public long getAverageArrivalTime() {
        Car[] allCars = cars.selectAll();
        if (allCars.length == 0) {
            return 0;
        }

        long totalHours = 0;
        int validCars = 0;

        for (Car car : allCars) {
            if (car.getArrived() != null) {
                long hours = ChronoUnit.HOURS.between(car.getArrived(), LocalDateTime.now());
                totalHours += hours;
                validCars++;
            }
        }

        return validCars > 0 ? totalHours / validCars : 0;
    }

    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        Car[] allCars = cars.selectAll();
        Listable<Car> resultCars = new LinkedList<>(MAX_CAPACITY);

        for (Car car : allCars) {
            long duration = getParkingDuration(car.getLicensePlate());
            if (duration > thresholdHours) {
                resultCars.append(car);
            }
        }

        return resultCars.selectAll();
    }

    // Capacidade do estacionamento
    @Override
    public int getAvailableSpaces() {
        return MAX_CAPACITY - getTotalCars();
    }

    @Override
    public int getOccupancyRate() {
        return (getTotalCars() * 100) / MAX_CAPACITY;
    }

    @Override
    public boolean isParkingFull() {
        return getTotalCars() >= MAX_CAPACITY;
    }

    @Override
    public boolean isParkingEmpty() {
        return getTotalCars() == 0;
    }

    @Override
    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }
}