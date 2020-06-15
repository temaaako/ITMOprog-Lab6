package Commands;

import Controls.Storage;
import Human.HumanBeing;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "add_element"
 */
public  class Update  implements Command {
    Long id;
    HumanBeing human;

    public Update(Long id, HumanBeing human) {
        this.id = id;
        this.human = human;
    }

    @Override
    public Packet execute(Storage storage) {
        return storage.update(this.id, this.human);
    }

//    public  void execute(String strId, ArrayList<HumanBeing> list) {
//        try {
//            Long id = Long.parseLong(strId);
//            update(id, list);
//        } catch (Exception e) {
//            System.out.println("Некорректный аргумент");
//        }
//    }
}
