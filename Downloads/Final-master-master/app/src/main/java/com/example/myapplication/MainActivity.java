package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private List<Article>articles;
    private RecyclerView recyclerView;
    private NavigationView navigation;
    private ArrayList<String> topics;
    private View header;
    private ImageView userImage;
    private static final int RC_PHOTO_PICKER =  2;
    private FirebaseStorage storage;
    private StorageReference photoRef;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authListener;
    DatabaseReference articleRef;


    ArticleAdapter aAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //initialData();
        recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        articles = new ArrayList<>();





        //database  & initialize articles
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        articleRef = database.getReference("articles");
        articleRef.child ("0"). setValue (new Article (R.mipmap.lgbtq_image, "Boundaries and Expectations",false,"If you’ve spent some time on our site or spoken with one of our advocates, you’ve probably heard the words “boundaries” and “expectations” thrown around a lot. Understanding these concepts and being able to talk to your partner about them is important for any relationship to be healthy.  \n" +
                "But aren’t these two ideas pretty similar? What exactly is the difference between setting boundaries and defining expectations? What about broken boundaries vs. unmet expectations? It can definitely get a little confusing, so let’s get into it. \n" +
                "Expectations  \n" +
                "Our entire life experience is shaped by certain expectations. We make assumptions about how a situation should go, how people should act, even adjust our behavior to fall in line with what we think others expect of us. In relationships, sometimes our partners exceed our expectations, and we can be happily surprised. Someone whose previous partner was abusive may expect to be treated that way in their next relationship, only to find a new partner who is completely respectful and supportive. Other times, our expectations aren’t met, and that’s where we tend to get hurt. For example, maybe you expected that being in a relationship meant spending time with your partner every day, but your partner has a busy schedule, and you’re left feeling neglected.  \n" +
                "The problem is that expectations are usually based on assumptions, and anytime we assume we know what our partner is thinking (or assume they know what we’re thinking), we get into dangerous territory. A lot of the time, having an unmet expectation doesn’t mean you were wrong, it just means that it’s time to check in with your partner. Some expectations though, like wanting your partner to put you before everything else in their life, can be unrealistic and may need to be adjusted. It’s also important to remember that it’s never okay to engage in hurtful or abusive behavior when your expectations aren’t met.  \n" +
                "If you’re in a healthy relationship, we always recommend open, honest communication to find a solution to a conflict– and that includes conflict about relationship expectations. When we don’t communicate our expectations, we’re setting a standard for the relationship that hasn’t been agreed upon, and possibly even holding someone to a standard they didn’t know existed, which isn’t fair. We may not even realize we were expecting specific things out of a relationship until we don’t get them, and it can feel surprising, confusing, and even painful to realize you’re on a completely different page than your partner. That’s why it’s so important to talk through these situations together, to ensure that you both agree on what to expect moving forward. \n" +
                "Boundar aren’t met, partners may feel hurt, violated, disrespected, or even unsafe. If it’s safe to do so, we always recommend—you guessed it—open, honest, respectful communication to go over what happened, why it happened, and what should happen moving forward. If you ever feel like you can’t talk to your partner about how you’re feeling, that’s a huge warning sign that your relationship may be abusive. Try reaching out to one of our advocates instead to explore the situation further and talk through your options. Advocates can be reached 24/7/365 by phone at 1-866-331-9474, online chat at www.loveisrespect.org, or by texting LOVEIS to 22522.  ", "loveisrespect", new Date (), true, false, false));
        articleRef.child("1").setValue(new Article(R.mipmap.download,"Sexual Assault Awareness Month 2018: Embrace Your Voice",false,"April is Sexual Assault Awareness Month and this year’s theme is “Embrace Your Voice.” Between the Harvey Weinstein revelations, the #MeToo campaign, and the Time’s Up movement, we’ve watched survivors and allies all over the globe embrace their voices over the past few months in an important conversation around sexual harassment, assault, and abuse. It’s important to remember that there are a lot of people who will choose never to share their stories, and that’s okay, too! Embracing your voice around sexual assault can happen in so many ways, whether it’s calling out offensive “locker room talk” that normalizes sexual assault, guiding a friend to appropriate resources in a time of need, or simply practicing good consent strategies with your sexual partners. It’s important that we all work together to fight sexual assault in whatever way we feel we can, big or small.\n" +
                "\n" +
                "Here are some statistics about sexual assault and abuse from RAINN:\n" +
                "\n" +
                "Every 98 seconds, another American is sexually assaulted.\n" +
                "1 in 6 American women (17%) and 1 in 33 American men (3%) have been the victim of an attempted or completed rape in their lifetime.\n" +
                "Ages 18-24 carry a high risk of sexual violence:\n" +
                "23.1% of female and 5.4% of male undergraduate students have experienced rape or sexual assault through physical force, violence, or incapacitation.\n" +
                "21% of transgender, genderqueer and nonconforming college students, 18% of cisgender female college students, and 4% of cisgender male college students have been sexually assaulted.\n" +
                "18-24-year-old women who are not in college are 20% more likely to be sexually assaulted than students of the same age.\n" +
                "Native Americans are twice as likely to experience sexual assault compared to all races.\n" +
                "7 out of 10 rapes are committed by someone known to the victim.\n" +
                "25% of rapes are committed by a current or former intimate partner.\n" +
                "Perpetrators of sexual violence are less likely to go to jail or prison than any other criminals: out of every 1000 rapes, only 310 are reported to police and only 6 rapists will be incarcerated.\n" +
                "While loveisrespect is not available as a sexual assault resource, we are aware of the fact that sexual violence can be one of the many ways an abuser maintains power and control over their victim.\n" +
                "\n" +
                "If you or someone you know is experiencing sexual violence from an intimate partner or have questions about consent, please call us at 1-866-331-9474, 1-866-331-8453 (TTY), text loveis to 22522, or chat with us by clicking the Chat Now button. For support and resources specifically geared toward sexual assault (including sexual assault that is not part of an intimate partner relationship), please reach out to RAINN at 1-(800)-656-HOPE (4673) or chat at www.rainn.org.","Safety & Self Care",new Date(),false,false,true));



        articleRef.child("2").setValue(new Article(R.mipmap.download2,"The Growing Child: Adolescent (13 to 18 Years)",false,"Sexual and other physical maturation that occurs during puberty is a result of hormonal changes. In boys, it is difficult to know exactly when puberty is coming. There are changes that occur, but they occur gradually and over a period of time, rather than as a single event. While each male adolescent is different, the following are average ages when puberty changes may occur:\n" +
                "\n" +
                "Beginning of puberty: 9.5 to 14 years old\n" +
                "\n" +
                "First pubertal change: enlargement of the testicles\n" +
                "\n" +
                "Penis enlargement: begins approximately one year after the testicles begin enlarging\n" +
                "\n" +
                "Appearance of pubic hair: 13.5 years old\n" +
                "\n" +
                "Nocturnal emissions (or \"wet dreams\"): 14 years old\n" +
                "\n" +
                "Hair under the arms and on the face, voice change, and acne: 15 years old\n" +
                "\n" +
                "Girls also experience puberty as a sequence of events, but their pubertal changes usually begin before boys of the same age. Each girl is different and may progress through these changes differently. The following are average ages when puberty changes may occur:\n" +
                "\n" +
                "Beginning of puberty: 8 to 13 years\n" +
                "\n" +
                "First pubertal change: breast development\n" +
                "\n" +
                "Pubic hair development: shortly after breast development\n" +
                "\n" +
                "Hair under the arms: 12 years old\n" +
                "\n" +
                "Menstrual periods: 10 to 16.5 years old\n" +
                "\n" +
                "There are specific stages of development that both boys and girls go through when developing secondary sexual characteristics (the physical characteristics of males and females that are not involved in reproduction such as voice changes, body shape, pubic hair distribution, and facial hair). The following is a brief overview of the changes that occur:\n" +
                "\n" +
                "In boys, the initial puberty change is the enlargement of the scrotum and testes. At this point, the penis does not enlarge. Then, as the testes and scrotum continue to enlarge, the penis gets longer. Next, the penis will continue to grow in both size and length.\n" +
                "\n" +
                "In girls, the initial puberty change is the development of breast buds, in which the breast and nipple elevate. The areola (dark area of skin that surrounds the nipple of the breast) increases in size at this time. The breasts then continue to enlarge. Eventually, the nipples and the areolas will elevate again, forming another projection on the breasts. At the adult state, only the nipple remains elevated above the rest of the breast tissue.\n" +
                "\n" +
                "Pubic hair development is similar for both girls and boys. The initial growth of hair produces long, soft hair that is only in a small area around the genitals. This hair then becomes darker and coarser as it continues to spread. The pubic hair eventually looks like adult hair, but in a smaller area. It may spread to the thighs and, sometimes, up the stomach.\n" +
                "\n" +
                "What does my adolescent understand?\n" +
                "The teenage years bring many changes, not only physically, but also mentally and socially. During these years, adolescents increase their ability to think abstractly and eventually make plans and set long-term goals. Each child may progress at a different rate and may have a different view of the world. In general, the following are some of the abilities that may be evident in your adolescent:\n" +
                "\n" +
                "Develops the ability to think abstractly\n" +
                "\n" +
                "Is concerned with philosophy, politics, and social issues\n" +
                "\n" +
                "Thinks long-term\n" +
                "\n" +
                "Sets goals\n" +
                "\n" +
                "Compares one's self to one's peers\n" +
                "\n" +
                "As your adolescent begins to struggle for independence and control, many changes may occur. The following are some of the issues that may be involved with your adolescent during these years:\n" +
                "\n" +
                "Wants independence from parents\n" +
                "\n" +
                "Peer influence and acceptance becomes very important\n" +
                "\n" +
                "Romantic/sexual relationships become important\n" +
                "\n" +
                "May be in love\n" +
                "\n" +
                "Has long-term commitment in relationship\n" +
                "\n" +
                "How to assist your adolescent in developing socially\n" +
                "Consider the following as ways to foster your adolescent's social abilities:\n" +
                "\n" +
                "Encourage your adolescent to take on new challenges.\n" +
                "\n" +
                "Talk with your adolescent about not losing sight of one's self in group relations.\n" +
                "\n" +
                "Encourage your adolescent to talk to a trusted adult about problems or concerns, even if it is not you he or she chooses to talk with.\n" +
                "\n" +
                "Discuss ways to manage and handle stress.\n" +
                "\n" +
                "Provide consistent, loving discipline with limits, restrictions, and rewards.\n" +
                "\n" +
                "Find ways to spend time together.","Stanford Children's Health",new Date(),false,true,false));


        //user image (photos) storage
        storage = FirebaseStorage.getInstance();
        photoRef = storage.getReference().child("user_images");




        //handle spinner events

        topics = new ArrayList<>();
        topics.add("Popular");
        topics.add("Relationship");
        topics.add("Sexual Behavior");
        topics.add("Sexual Health");

        Spinner spinner = (Spinner) findViewById(R.id.spinner_nav);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,topics));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item){
                    case "Relationship":
                        Query query1 = articleRef.orderByChild("isRelationship").equalTo(true);
                        query1.addListenerForSingleValueEvent(valueEventListener);
                        break;
                    case "Sexual Behavior":
                        Query query2 = articleRef.orderByChild("isSexualBehavior").equalTo(true);
                        query2.addListenerForSingleValueEvent(valueEventListener);
                        break;
                    case "Sexual Health":
                        Query query3 = articleRef.orderByChild("isSexualHealth").equalTo(true);
                        query3.addListenerForSingleValueEvent(valueEventListener);
                        break;
                    case "Popular":
                        articleRef.orderByKey().addListenerForSingleValueEvent(valueEventListener);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });








        // handle toolbar on the left side events
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        navigation = findViewById(R.id.nav_view);
        header = navigation.getHeaderView(0);
        userImage = header.findViewById(R.id.user_image);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch(id){
                    case R.id.logOut:
                        startActivity(new Intent(MainActivity.this,PersonalInfo.class));

                    case R.id.nav_view:
                        Query query4 = articleRef.orderByChild("isViewed").equalTo(true);
                        query4.addListenerForSingleValueEvent(valueEventListener);
                        break;
                    case R.id.nav_save:
                        Query query5 = articleRef.orderByChild("articleIsSaved").equalTo(true);
                        query5.addListenerForSingleValueEvent(valueEventListener);
                        break;



                }
                return false;
            }
        });




        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        //Firebase auth
        mAuth = FirebaseAuth.getInstance();


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currentUser != null){



                }else{
                    Intent intent = new Intent(MainActivity.this,PersonalInfo.class);
                    startActivity(intent);
                }
            }
        };



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(MainActivity.this,PersonalInfo.class);
            startActivity(intent);
        }


        articleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                articles.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Article article = snapshot.getValue(Article.class);
                        articles.add(article);
                    }
                }
                aAdapter = new ArticleAdapter(articles,MainActivity.this);
                recyclerView.setAdapter(aAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            articles.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Article article = snapshot.getValue(Article.class);
                    articles.add(article);
                }
            }
            ArticleAdapter aAdapter = new ArticleAdapter(articles,MainActivity.this);
            recyclerView.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      
        if(requestCode == RC_PHOTO_PICKER ){
            if(resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                final StorageReference imageRef = photoRef.child(selectedImageUri.getLastPathSegment());
                imageRef.putFile(selectedImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        Task<Uri> downloadUrl = imageRef.getDownloadUrl();
                        return downloadUrl;
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                            Uri downloadUri = task.getResult();
                            Glide.with(getApplicationContext())
                                    .load(downloadUri.toString())
                                    .into(userImage);
                        } else {
                            Toast.makeText(MainActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }

    }





    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }



    public void view_profile(View view) {
        Intent intent=new Intent(MainActivity.this,PersonalInfo.class);
        startActivity(intent);
    }



}
