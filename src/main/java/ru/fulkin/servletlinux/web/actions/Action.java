package ru.fulkin.servletlinux.web.actions;

import javax.servlet.ServletException;

public enum Action {
    ALL("all", new AllActionType()),
    CREATE("create", new CreateActionType()),
    UPDATE("update", new UpdateActionType()),
    DELETE("delete", new DeleteAction());

    private String actionName;
    private ActionType actionType;

    Action(String actionName, ActionType actionType) {
        this.actionName = actionName;
        this.actionType = actionType;
    }

    public static Action getAction(String code) throws ServletException {
        for (Action action : Action.values()) {
            if (code.equals(action.actionName)) {
                return action;
            }
        }
        throw new ServletException();
    }

    public ActionType getActionName() {
        return actionType;
    }
}
