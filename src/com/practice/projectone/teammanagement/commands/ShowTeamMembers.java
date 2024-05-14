package com.practice.projectone.teammanagement.commands;

import com.practice.projectone.teammanagement.core.contracts.TeamRepository;
import com.practice.projectone.teammanagement.models.contracts.Team;
import com.practice.projectone.teammanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamMembers extends BaseCommand{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TEAM_HAS_NO_MEMBERS = "This team has no members!";

    public ShowTeamMembers(TeamRepository teamRepository) {
        super(teamRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);

        return showTeamMembers(teamName);
    }

    private String showTeamMembers(String teamName){
        Team team = getTeamRepository().findTeamByName(teamName);
        StringBuilder builder = new StringBuilder();

        if (team.getMembers().isEmpty()){
            builder.append(TEAM_HAS_NO_MEMBERS);
        } else {
            builder.append("--MEMBERS--");
            for (int i = 0; i < team.getMembers().size(); i++) {
                builder.append(System.lineSeparator());
                builder.append(i + 1);
                builder.append(". ");
                builder.append(team.getMembers().get(i).toString());
            }
        }
        return builder.toString();
    }
}