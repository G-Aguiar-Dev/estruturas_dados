package com.example.dao;

import com.example.dao.repository.LinkedStack;
import com.example.dao.repository.Stackable;
import com.example.dao.model.Car;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CarDAOLinkedStack implements CarDAO {

    /**
     * Pilha principal para armazenamento dos carros.
     * Utiliza uma implementação baseada em lista encadeada com capacidade inicial de 20 elementos.
     */
    private Stackable<Car> cars = new LinkedStack<>(20);
    private static final int MAX_CAPACITY = 20;

    // Operações básicas CRUD
    /**
     * Adiciona um novo carro à pilha.
     * 
     * O carro é inserido no topo da pilha, seguindo o princípio LIFO.
     * Esta operação tem complexidade O(1).
     * 
     * @param car o carro a ser adicionado (não pode ser null)
     * @throws IllegalArgumentException se o carro for null
     */
    @Override
    public void addCar(Car car) {
        cars.push(car);
    }

    /**
     * Retorna um carro pela sua placa de licença.
     * 
     * Esta operação localiza um carro pela placa de licença sem removê-lo da pilha.
     * 
     * @param plateLicense a placa de licença do carro
     * @return o carro encontrado ou null se não existir
     */
    @Override
    public Car getCar(String plateLicense) {
        return getCarByLicensePlate(plateLicense);
    }

    /**
     * Retorna todos os carros da pilha em um array.
     * 
     * Esta operação desempilha todos os carros, cria um array com eles
     * e reempilha na ordem original, preservando a estrutura da pilha.
     * 
     * @return array contendo todos os carros da pilha
     */
    @Override
    public Car[] getAllCars() {
        return stackToArray(cars);
    }

    /**
     * Atualiza um carro existente na pilha.
     * 
     * Esta operação localiza o carro pela placa de licença e substitui pela nova versão.
     * A pilha é reconstruída mantendo a ordem original dos outros carros.
     * 
     * @param newCar o carro atualizado (deve ter a mesma placa do carro original)
     */
    @Override
    public void updateCar(Car newCar) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getLicensePlate().equals(newCar.getLicensePlate())) {
                tempCars.push(newCar);
            } else {
                tempCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
    }

    /**
     * Remove um carro da pilha pela sua placa de licença.
     * 
     * Esta operação localiza o carro pela placa, remove-o da pilha
     * e reconstrói a pilha sem o carro removido, mantendo a ordem dos outros.
     * 
     * @param plateLicense a placa de licença do carro a ser removido
     * @return o carro removido ou null se não for encontrado
     */
    @Override
    public Car deleteCar(String plateLicense) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Car resultCar = null;

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getLicensePlate().equals(plateLicense)) {
                resultCar = car;
                break;
            } else {
                tempCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    // Operações de consulta específicas para carros
    /**
     * Busca um carro pela sua placa de licença sem alterar a pilha.
     * 
     * Esta operação percorre todos os carros preservando a ordem da pilha
     * e retorna o primeiro carro encontrado com a placa especificada.
     * 
     * @param licensePlate a placa de licença do carro a ser buscado
     * @return o carro encontrado ou null se não existir
     */
    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Car resultCar = null;

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getLicensePlate() != null && car.getLicensePlate().equals(licensePlate)) {
                resultCar = car;
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    /**
     * Busca todos os carros de uma marca específica.
     * 
     * A busca é feita de forma case-insensitive, comparando a marca
     * de cada carro com o parâmetro fornecido.
     * 
     * @param mark o nome da marca a ser buscada
     * @return array com todos os carros da marca especificada
     */
    @Override
    public Car[] getCarsByMark(String mark) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getMark() != null && car.getMark().equalsIgnoreCase(mark)) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    /**
     * Busca todos os carros de um modelo específico.
     * 
     * A busca é feita de forma case-insensitive, comparando o modelo
     * de cada carro com o parâmetro fornecido.
     * 
     * @param model o nome do modelo a ser buscado
     * @return array com todos os carros do modelo especificado
     */
    @Override
    public Car[] getCarsByModel(String model) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getModel() != null && car.getModel().equalsIgnoreCase(model)) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    /**
     * Busca todos os carros de uma cor específica.
     * 
     * A busca é feita de forma case-insensitive, comparando a cor
     * de cada carro com o parâmetro fornecido.
     * 
     * @param color a cor a ser buscada
     * @return array com todos os carros da cor especificada
     */
    @Override
    public Car[] getCarsByColor(String color) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    /**
     * Busca todos os carros de um proprietário específico.
     * 
     * A busca é feita de forma case-insensitive, comparando o nome do proprietário
     * de cada carro com o parâmetro fornecido.
     * 
     * @param owner o nome do proprietário a ser buscado
     * @return array com todos os carros do proprietário especificado
     */
    @Override
    public Car[] getCarsByOwner(String owner) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    /**
     * Busca todos os carros chegados dentro de um intervalo de tempo.
     * 
     * A busca inclui carros com momento de chegada maior ou igual ao momento inicial
     * e menor ou igual ao momento final especificados. Apenas carros com data não nula são considerados.
     * 
     * @param initialMoment o momento inicial (inclusivo)
     * @param finalMoment o momento final (inclusivo)
     * @return array com todos os carros chegados no intervalo especificado
     */
    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null &&
                !car.getArrived().isBefore(initialMoment) &&
                !car.getArrived().isAfter(finalMoment)) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    // Operações de análise e estatísticas
    /**
     * Encontra o carro com a chegada mais recente (mais novo na pilha).
     * 
     * Esta operação percorre todos os carros comparando suas datas de chegada
     * e retorna o carro com a data mais recente.
     * 
     * @return o carro com a chegada mais recente ou null se a pilha estiver vazia
     */
    @Override
    public Car getCarByNewestArrival() {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Car resultCar = null;

        // Desempilhar todos os carros
        if (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null) {
                resultCar = car;
            }

            while (!cars.isEmpty()) {
                car = cars.pop();
                tempCars.push(car);
                if (car.getArrived() != null) {
                    if (resultCar != null && car.getArrived().isBefore(resultCar.getArrived())) {
                        resultCar = car;
                    }
                }
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    /**
     * Encontra o carro com a chegada mais antiga (mais velho na pilha).
     * 
     * Esta operação percorre todos os carros comparando suas datas de chegada
     * e retorna o carro com a data mais antiga.
     * 
     * @return o carro com a chegada mais antiga ou null se a pilha estiver vazia
     */
    @Override
    public Car getCarByOldestArrival() {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Car resultCar = null;

        // Desempilhar todos os carros
        if (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null) {
                resultCar = car;
            }

            while (!cars.isEmpty()) {
                car = cars.pop();
                tempCars.push(car);
                if (car.getArrived() != null) {
                    if (resultCar != null && car.getArrived().isAfter(resultCar.getArrived())) {
                        resultCar = car;
                    }
                }
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    // Operações de relatório e estatísticas
    /**
     * Retorna uma representação em string de todos os carros da pilha.
     * 
     * Utiliza o método toString() da pilha para gerar a representação
     * de todos os carros armazenados.
     * 
     * @return string representando todos os carros da pilha
     */
    @Override
    public String printCars() {
        return cars.toString();
    }

    /**
     * Retorna o número total de carros na pilha.
     * 
     * Esta operação conta todos os elementos da pilha sem removê-los,
     * preservando a estrutura original.
     * 
     * @return o número total de carros na pilha
     */
    @Override
    public int getTotalCars() {
        return countElements(cars);
    }

    /**
     * Encontra a marca de carro mais popular (com mais ocorrências) na pilha.
     * 
     * @return a marca mais popular ou uma string vazia se a pilha estiver vazia
     */
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

    /**
     * Encontra o modelo de carro mais popular (com mais ocorrências) na pilha.
     * 
     * @return o modelo mais popular ou uma string vazia se a pilha estiver vazia
     */
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

    /**
     * Encontra a cor de carro mais popular (com mais ocorrências) na pilha.
     * 
     * @return a cor mais popular ou uma string vazia se a pilha estiver vazia
     */
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

    /**
     * Calcula a duração do estacionamento para um carro específico em horas.
     * 
     * A duração é calculada desde o momento da chegada até o momento atual.
     * 
     * @param plateLicense a placa de licença do carro
     * @return o número de horas estacionado ou -1 se o carro não for encontrado
     */
    @Override
    public long getParkingDuration(String plateLicense) {
        Car car = getCarByLicensePlate(plateLicense);
        if (car != null && car.getArrived() != null) {
            return ChronoUnit.HOURS.between(car.getArrived(), LocalDateTime.now());
        }
        return -1;
    }

    /**
     * Busca todos os carros com uma duração de estacionamento dentro de um intervalo.
     * 
     * @param minHours a duração mínima em horas (inclusiva)
     * @param maxHours a duração máxima em horas (inclusiva)
     * @return array com todos os carros no intervalo de duração especificado
     */
    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            long duration = getParkingDuration(car.getLicensePlate());
            if (duration >= minHours && duration <= maxHours) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    // Operações de gerenciamento
    /**
     * Verifica se um carro está estacionado (presente na pilha).
     * 
     * Esta operação verifica se existe um carro com a placa especificada
     * na pilha, sem removê-lo.
     * 
     * @param plateLicense a placa de licença do carro a ser verificada
     * @return true se o carro estiver estacionado, false caso contrário
     */
    @Override
    public boolean isCarInPlaced(String plateLicense) {
        return getCarByLicensePlate(plateLicense) != null;
    }

    /**
     * Remove todos os carros da pilha.
     * 
     * Esta operação esvazia completamente a pilha, removendo
     * todos os carros armazenados. A operação é irreversível.
     */
    @Override
    public void clearAllCars() {
        while (!cars.isEmpty()) {
            cars.pop();
        }
    }

    /**
     * Remove todos os carros chegados antes de uma data específica.
     * 
     * @param date a data limite (carros com chegada anterior são removidos)
     */
    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getArrived() == null || !car.getArrived().isBefore(date)) {
                tempCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
    }

    /**
     * Remove todos os carros de um proprietário específico.
     * 
     * @param owner o nome do proprietário cujos carros serão removidos
     */
    @Override
    public void removeCarsByOwner(String owner) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getOwnerName() == null || !car.getOwnerName().equalsIgnoreCase(owner)) {
                tempCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
    }

    /**
     * Calcula o tempo médio de chegada entre todos os carros.
     * 
     * @return o tempo médio em horas desde a chegada do carro mais antigo
     */
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

    /**
     * Busca todos os carros com uma duração de estacionamento superior a um limite.
     * 
     * @param thresholdHours o número de horas limite
     * @return array com todos os carros estacionados por mais que o limite
     */
    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        Stackable<Car> resultCars = new LinkedStack<>(MAX_CAPACITY);

        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            long duration = getParkingDuration(car.getLicensePlate());
            if (duration > thresholdHours) {
                resultCars.push(car);
            }
        }

        // Reempilhar na ordem original
        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(resultCars);
    }

    // Capacidade do estacionamento
    /**
     * Retorna o número de vagas disponíveis no estacionamento.
     * 
     * @return o número de vagas disponíveis
     */
    @Override
    public int getAvailableSpaces() {
        return MAX_CAPACITY - getTotalCars();
    }

    /**
     * Retorna a taxa de ocupação do estacionamento em porcentagem.
     * 
     * @return a porcentagem de ocupação (0-100)
     */
    @Override
    public int getOccupancyRate() {
        return (getTotalCars() * 100) / MAX_CAPACITY;
    }

    /**
     * Verifica se o estacionamento está completamente cheio.
     * 
     * @return true se não houver vagas disponíveis, false caso contrário
     */
    @Override
    public boolean isParkingFull() {
        return getTotalCars() >= MAX_CAPACITY;
    }

    /**
     * Verifica se o estacionamento está vazio.
     * 
     * @return true se não houver carros, false caso contrário
     */
    @Override
    public boolean isParkingEmpty() {
        return getTotalCars() == 0;
    }

    /**
     * Retorna a capacidade máxima do estacionamento.
     * 
     * @return o número máximo de carros que podem ser estacionados
     */
    @Override
    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    // Métodos auxiliares
    /**
     * Conta o número de elementos em uma pilha sem removê-los.
     * 
     * Este método auxiliar percorre todos os elementos da pilha,
     * conta-os e restaura a pilha original.
     * 
     * @param stack a pilha a ser contada
     * @return o número de elementos na pilha
     */
    private int countElements(Stackable<Car> stack) {
        Stackable<Car> tempCars = new LinkedStack<>(MAX_CAPACITY);
        int count = 0;

        while (!stack.isEmpty()) {
            tempCars.push(stack.pop());
            count++;
        }

        // Restaurar a pilha original
        while (!tempCars.isEmpty()) {
            stack.push(tempCars.pop());
        }

        return count;
    }

    /**
     * Converte uma pilha de resultados em um array de carros.
     * 
     * Este método auxiliar é usado para converter pilhas temporárias
     * que contêm resultados de filtros em arrays.
     * 
     * @param stack a pilha contendo os carros a serem convertidos
     * @return array contendo todos os carros da pilha
     */
    private Car[] stackToArray(Stackable<Car> stack) {
        Car[] resultArrayCars = new Car[countElements(stack)];
        int index = 0;
        while (!stack.isEmpty()) {
            resultArrayCars[index] = stack.pop();
            index++;
        }
        return resultArrayCars;
    }
}