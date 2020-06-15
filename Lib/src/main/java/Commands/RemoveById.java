package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "remove_by_id"
 */
public  class RemoveById  implements Command {
    Long id;

    public RemoveById(Long id) {
        this.id = id;
    }

    @Override
    public Packet execute(Storage storage) {
        return storage.removeById(this.id);
    }


//    public   void execute(String strId, ArrayList<HumanBeing> list) {
//        try {
//            Long id = Long.parseLong(strId);
//            removeById(id, list);
//        }catch (Exception e){
//            System.out.println("Некорректный аргумент");
//        }
//
//    }
}
