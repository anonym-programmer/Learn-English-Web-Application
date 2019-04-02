package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserDetailsService {

    UserDetailsRepository repository;

    void saveAndFlush(UserDetails details) {
        repository.saveAndFlush(details);
    }

    void updateUserDetails(UserDetails details) {
        long currentExpierience = Long.parseLong(details.getExpierience());

        if (currentExpierience < 800) {
            checkLowerOrEqualToGoldRank(details, currentExpierience);
        } else {
            checkHigherThanGoldRank(details, currentExpierience);
        }
    }

    private void checkLowerOrEqualToGoldRank(UserDetails details, long currentExpierience) {
        if (currentExpierience < 50) {
            details.setLevel("1");
            details.setCurrentRank(String.valueOf(UserRanks.BRONZE));
            details.setLeftExperienceToTheNextLevel(String.valueOf(50 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience * 100) / 49)));
        } else if (currentExpierience < 100) {
            details.setLevel("2");
            details.setCurrentRank(String.valueOf(UserRanks.SILVER));
            details.setLeftExperienceToTheNextLevel(String.valueOf(100 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 50) * 100) / (99 - 50)));
        } else if (currentExpierience < 200) {
            details.setLevel("3");
            details.setCurrentRank(String.valueOf(UserRanks.SILVER));
            details.setLeftExperienceToTheNextLevel(String.valueOf(200 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 100) * 100) / (199 - 100)));
        } else if ( currentExpierience < 300) {
            details.setLevel("4");
            details.setCurrentRank(String.valueOf(UserRanks.SILVER));
            details.setLeftExperienceToTheNextLevel(String.valueOf(300 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 200) * 100) / (299 - 200)));
        } else if (currentExpierience < 550) {
            details.setLevel("5");
            details.setCurrentRank(String.valueOf(UserRanks.GOLD));
            details.setLeftExperienceToTheNextLevel(String.valueOf(550 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 300) * 100) / (549 - 300)));
        } else {
            details.setLevel("6");
            details.setCurrentRank(String.valueOf(UserRanks.GOLD));
            details.setLeftExperienceToTheNextLevel(String.valueOf(800 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 550) * 100) / (799 - 550)));
        }
    }

    private void checkHigherThanGoldRank(UserDetails details, long currentExpierience) {
        if (currentExpierience < 1400) {
            details.setLevel("7");
            details.setCurrentRank(String.valueOf(UserRanks.PLATINUM));
            details.setLeftExperienceToTheNextLevel(String.valueOf(1400 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 800) * 100) / (1399 - 800)));
        } else if (currentExpierience < 2000) {
            details.setLevel("8");
            details.setCurrentRank(String.valueOf(UserRanks.PLATINUM));
            details.setLeftExperienceToTheNextLevel(String.valueOf(2000 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 1400) * 100) / (1999 - 1400)));
        } else if (currentExpierience < 3400) {
            details.setLevel("9");
            details.setCurrentRank(String.valueOf(UserRanks.DIAMOND));
            details.setLeftExperienceToTheNextLevel(String.valueOf(3400 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 2000) * 100) / (3399 - 2000)));
        } else if (currentExpierience < 4500) {
            details.setLevel("10");
            details.setCurrentRank(String.valueOf(UserRanks.DIAMOND));
            details.setLeftExperienceToTheNextLevel(String.valueOf(4500 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 3400) * 100) / (4499 - 3400)));
        } else if (currentExpierience < 7000) {
            details.setLevel("11");
            details.setCurrentRank(String.valueOf(UserRanks.MASTER));
            details.setLeftExperienceToTheNextLevel(String.valueOf(7000 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 4500) * 100) / (6999 - 4500)));
        } else if (currentExpierience < 10000) {
            details.setLevel("12");
            details.setCurrentRank(String.valueOf(UserRanks.MASTER));
            details.setLeftExperienceToTheNextLevel(String.valueOf(10000 - currentExpierience));
            details.setCurrentExperienceInPercents(String.valueOf(((currentExpierience - 7000) * 100) / (9999 - 7000)));
        } else {
            details.setLevel("13");
            details.setCurrentRank(String.valueOf(UserRanks.CHALLENGER));
            details.setLeftExperienceToTheNextLevel("0");
            details.setCurrentExperienceInPercents("100");
        }
    }

    UserDetails findUserDetailsById(long id) {
        return repository.findUserDetailsById(id);
    }
}
