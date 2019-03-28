package com.galvanize.springplayground.json.model.activity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class Activity {
    private User user;
    private Status status;

    public Activity() {
    }

    //Compact View
    @JsonView(Views.CompactView.class)
    @JsonGetter("user")
    public String getUserName(){ return this.user.getUsername(); }
    @JsonView(Views.CompactView.class)
    @JsonGetter("date")
    public String getStatusDate(){ return this.getStatus().getDate(); }
    @JsonView(Views.CompactView.class)
    @JsonGetter("statusText")
    public String getStatusText(){ return this.getStatus().getText(); }

    //DetailView
    @JsonView(Views.DetailView.class)
    @JsonGetter("userId")
    public long getUserId(){ return user.getId(); }
    @JsonView(Views.DetailView.class)
    @JsonGetter("email")
    public String getUserPrimaryEmail(){ return this.getUser().getPrimaryEmail().getAddress(); }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class User{
        private long id;
        private String username;
        private List<Email> emails;

        public User() {
        }

        public User.Email getPrimaryEmail(){
            if(emails == null){
                return null;
            }
            for(Email e : emails){
                if(e.isPrimary()) {
                    return e;
                }
            }

            return emails.isEmpty() ? null : emails.get(0);

        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<Email> getEmails() {
            return emails;
        }

        public void setEmails(List<Email> emailList) {
            this.emails = emailList;
        }

        public static class Email{
            private long id;
            private String address;
            private boolean primary;

            public Email() {
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public boolean isPrimary() {
                return primary;
            }

            public void setPrimary(boolean primary) {
                this.primary = primary;
            }
        }
    }

    public static class Status{
        private String text;
        private String date;

        public Status() {
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
