package Commands;

import Controls.Storage;
import Human.Car;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "filter_greater_than_car"
 */
public  class FilterGreaterThanCar implements Command {
Car car;

    public FilterGreaterThanCar(Car car) {
        this.car = car;
    }

    @Override
    public Packet execute(Storage storage) {
        return storage.filterGreaterThanCar(this.car);
    }
}
