package az.task.demo.CustomExceptions;

import az.task.demo.CustomExceptions.BaseExceptionClass.BaseNotFoundException;

public class StatusNotFoundException extends BaseNotFoundException {

        private final static String message=" NOT FOUND , STATUS =";

        public StatusNotFoundException(int id,String domainType) {
            super(id,domainType+message);
        }

        @Override
        public String toString() {
            return "TaskNotFound{" +
                    "id=" + id +
                    '}';
        }
}
