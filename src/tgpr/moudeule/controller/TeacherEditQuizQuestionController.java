package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherEditQuizQuestionView;
import tgpr.moudeule.view.View;

import java.util.List;

public class TeacherEditQuizQuestionController extends Controller {

    private int questionId;

    public TeacherEditQuizQuestionController(int questionId) {
        this.questionId = questionId;
    }

    private int page = 1;

    private final static int NUMBER_DISPLAY_LINE = 6;

    @Override
    public void run() {
        var question = Question.getById(questionId);

        var view = new TeacherEditQuizQuestionView();

        try {
            String res;
            do {
                var options = Option.getOptionsByQuestion(questionId);
                int nbPages = (int) Math.ceil(options.size() / ((double) NUMBER_DISPLAY_LINE));
                view.displayHeaderWithPseudoAndPageNumber(question, page, nbPages);
                view.displayTitle(question);
                view.displayType(question);
                view.displayOption(question, page, NUMBER_DISPLAY_LINE);
                view.displayBottomOptions(page, nbPages);

                res = view.askForString().toUpperCase();

                if (res.equals("1")) {
                    String title = view.askForTitle(question.getTitle());
                    question.setTitle(title);
                    question.save();

                } else if (res.equals("2")) {
                    if (question.getType().equalsIgnoreCase("QRM") && Question.getNumberTrueAnswers(question) < 2 || question.getType().equalsIgnoreCase("QCM")) {
                        String type = view.askForType(question.getType());
                        while (!Question.isValidType(type)) {
                            view.showTypeError();
                            type = view.askForType(question.getType());
                        }
                        question.setType(type);
                        question.save();
                    } else {
                        view.showTypeErrorManyCorrectAnswers();
                    }

                } else if (res.equals("0")) {
                    try {
                        String optionTitle = view.askForOptionTitle();
                        List<String> existingOptions = Option.getAllOptionTitlesOfQuestions(questionId);
                        if(!existingOptions.contains(optionTitle)) {
                            int optionCorrect = view.askForOptionCorrect();
                            if (optionCorrect == 1 && question.getType().equalsIgnoreCase("QCM")) {
                                for (Option option : options) {
                                    option.setCorrect(0);
                                    option.save();
                                }
                            }
                            Option newOption = new Option(optionTitle, optionCorrect, questionId);
                            if (!(optionTitle == null || optionTitle.equals(""))) {
                                newOption.setTitle(optionTitle);
                                newOption.save();
                            }
                        } else {
                            view.showTypeErrorExistingOption();
                        }
                    } catch (View.ActionInterruptedException e) {
                    }

                } else if (isParsable(res) && Integer.parseInt(res) >= 3 && Integer.parseInt(res) < 3 + options.size()) {
                    try {
                        int index = Integer.parseInt(res) - 3;
                        Option currentOption = options.get(index);
                        String subRes;
                        view.displaySubOptions();

                        subRes = view.askForString().toUpperCase();
                        if (subRes.equals("1")) {
                            String title = view.askForOptionTitle(currentOption.getTitle());
                            currentOption.setTitle(title);
                            currentOption.save();
                        } else if (subRes.equals("2")) {
                            int option = view.askForOptionCorrect(currentOption.getCorrect());
                            if (option == 1 && question.getType().equalsIgnoreCase("QCM")) {
                                for (Option o : options) {
                                    o.setCorrect(0);
                                    o.save();
                                }
                            }
                            if (option == 0 && question.getType().equalsIgnoreCase("QRM") && option != currentOption.getCorrect() && Question.getNumberTrueAnswers(question) == 1) {
                                view.showTypeErrorNoCorrectAnswer();
                            } else {
                                currentOption.setCorrect(option);
                                currentOption.save();
                            }
                        } else if (subRes.equals("3")) {
                            currentOption.delete();
                        }
                    } catch (View.ActionInterruptedException e) {

                    }
                } else if (res.equals("S") && page < nbPages && nbPages > 1) {
                    ++page;
                } else if (res.equals("P") && page > 1) {
                    --page;
                } else if (res.equals("R")) {
                    new TeacherEditQuizController(Quiz.getByQuestionId(questionId).getId()).run();
                }
            } while (!res.equals("Q"));
        } catch (View.ActionInterruptedException e) {
        }
        MoudeuleApp.logout();
    }


}
