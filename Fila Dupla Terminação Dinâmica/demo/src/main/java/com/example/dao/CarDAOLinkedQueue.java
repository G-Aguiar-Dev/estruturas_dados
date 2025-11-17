package com.example.dao;

import com.example.dao.repository.LinkedQueue;
import com.example.dao.repository.Queueable;
import com.example.dao.model.Car;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementação do DAO (Data Access Object) para gerenciamento de carros
 * utilizando uma estrutura de dados do tipo fila com dupla terminação (DEQue).
 * 
 * Esta classe implementa todas as operações CRUD (Create, Read, Update, Delete)
 * e operações de consulta específicas para carros, mantendo os dados em uma
 * estrutura de fila que preserva a ordem FIFO (First In, First Out).
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-10-20
 * @see CarDAO
 * @see Car
 * @see DEQueable
 * @see LinkedDEQue
 */
public class CarDAOLinkedQueue implements CarDAO {

    private Queueable<Car> cars = new LinkedQueue<>(20);
    private static final int MAX_CAPACITY = 20;

    // Operações básicas CRUD
    @Override
    public void addCar(Car car) {
        cars.EndEnqueue(car);
    }

    @Override
    public Car getCar(String plateLicense) {
        return getCarByLicensePlate(plateLicense);
    }

    @Override
    public Car[] getAllCars() {
        return queueToArray(cars);
    }

    @Override
    public void updateCar(Car newCar) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            if (car.getLicensePlate().equals(newCar.getLicensePlate())) {
                tempCars.EndEnqueue(newCar);
            } else {
                tempCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }
    }

    @Override
    public Car deleteCar(String plateLicense) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            if (car.getLicensePlate().equals(plateLicense)) {
                resultCar = car;
            } else {
                tempCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return resultCar;
    }

    // Operações de consulta específicas para carros
    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getLicensePlate() != null && car.getLicensePlate().equals(licensePlate)) {
                resultCar = car;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return resultCar;
    }

    @Override
    public Car[] getCarsByMark(String mark) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getMark() != null && car.getMark().equalsIgnoreCase(mark)) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
    }

    @Override
    public Car[] getCarsByModel(String model) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getModel() != null && car.getModel().equalsIgnoreCase(model)) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
    }

    @Override
    public Car[] getCarsByColor(String color) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
    }

    @Override
    public Car[] getCarsByOwner(String owner) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
    }

    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getArrived() != null &&
                !car.getArrived().isBefore(initialMoment) &&
                !car.getArrived().isAfter(finalMoment)) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
    }

    // Operações de análise e estatísticas
    @Override
    public Car getCarByNewestArrival() {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getArrived() != null) {
                if (resultCar == null || car.getArrived().isAfter(resultCar.getArrived())) {
                    resultCar = car;
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return resultCar;
    }

    @Override
    public Car getCarByOldestArrival() {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            if (car.getArrived() != null) {
                if (resultCar == null || car.getArrived().isBefore(resultCar.getArrived())) {
                    resultCar = car;
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return resultCar;
    }

    // Operações de relatório e estatísticas
    @Override
    public String printCars() {
        return cars.printFrontToRear();
    }

    @Override
    public int getTotalCars() {
        return countElements(cars);
    }

    @Override
    public String getMostPopularMark() {
        String mostPopularMark = "";
        int maxCount = 0;

        Car[] allCars = getAllCars();

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

        Car[] allCars = getAllCars();

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

        Car[] allCars = getAllCars();

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
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            long duration = getParkingDuration(car.getLicensePlate());
            if (duration >= minHours && duration <= maxHours) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
    }

    // Operações de gerenciamento
    @Override
    public boolean isCarInPlaced(String plateLicense) {
        return getCarByLicensePlate(plateLicense) != null;
    }

    @Override
    public void clearAllCars() {
        while (!cars.isEmpty()) {
            cars.BeginDequeue();
        }
    }

    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            if (car.getArrived() == null || !car.getArrived().isBefore(date)) {
                tempCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }
    }

    @Override
    public void removeCarsByOwner(String owner) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            if (car.getOwnerName() == null || !car.getOwnerName().equalsIgnoreCase(owner)) {
                tempCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }
    }

    @Override
    public long getAverageArrivalTime() {
        Car[] allCars = getAllCars();
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
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        Queueable<Car> resultCars = new LinkedQueue<>(MAX_CAPACITY);

        while (!cars.isEmpty()) {
            Car car = cars.BeginDequeue();
            tempCars.EndEnqueue(car);
            long duration = getParkingDuration(car.getLicensePlate());
            if (duration > thresholdHours) {
                resultCars.EndEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.EndEnqueue(tempCars.BeginDequeue());
        }

        return queueToArray(resultCars);
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

    // Métodos auxiliares
    private int countElements(Queueable<Car> queue) {
        Queueable<Car> tempCars = new LinkedQueue<>(MAX_CAPACITY);
        int count = 0;

        while (!queue.isEmpty()) {
            tempCars.EndEnqueue(queue.BeginDequeue());
            count++;
        }

        while (!tempCars.isEmpty()) {
            queue.EndEnqueue(tempCars.BeginDequeue());
        }

        return count;
    }

    private Car[] queueToArray(Queueable<Car> queue) {
        Car[] resultArrayCars = new Car[countElements(queue)];
        int index = 0;
        while (!queue.isEmpty()) {
            resultArrayCars[index] = queue.BeginDequeue();
            index++;
        }
        return resultArrayCars;
    }
}