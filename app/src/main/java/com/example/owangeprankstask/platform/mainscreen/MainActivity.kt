package com.example.owangeprankstask.platform.mainscreen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.owangeprankstask.R
import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.util.toModels
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private val viewModel: MainViewModel by inject()
    private lateinit var adapter: ContactAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
        contactObserver()
        initViews()
    }

    private fun initViews() {
        adapter = ContactAdapter {
            val number = Uri.parse("tel:" + it.phone)
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(callIntent)
        }
        contactsRecyclerView.adapter = adapter
    }

    private fun contactObserver() {
        viewModel.observeContacts().observe(this, Observer {
            it?.let {
                adapter.setData(it.toModels())
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LoaderManager.getInstance(this).initLoader(CONTACTS_LOADER_ID, null, this)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 10)
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return when (id) {
            CONTACTS_LOADER_ID -> contactsLoader()
            else -> CursorLoader(applicationContext)
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        //The framework will take care of closing the
        // old cursor once we return.
        //The framework will take care of closing the
        // old cursor once we return.
        val contacts: List<ContactItem> = contactsFromCursor(data)
        viewModel.saveContacts(contacts)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }

    private fun contactsLoader(): Loader<Cursor> {
        val contactsUri: Uri =
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI // The content URI of the phone contacts
        val projection = arrayOf( // The columns to return for each row
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        return CursorLoader(
            applicationContext,
            contactsUri,
            projection,
            null,
            null,
            null
        )
    }

    private fun contactsFromCursor(cursor: Cursor?): List<ContactItem> {
        val contacts: MutableList<ContactItem> = ArrayList()
        cursor?.let {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val id =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        val phone =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        val number =
                            cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        contacts.add(
                            ContactItem(
                                id,
                                name,
                                phone,
                                number
                            )
                        )
                    }
                } while (cursor.moveToNext())
            }
        }
        return contacts
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(
                    this,
                    "Permission denied to read your contacts",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private const val CONTACTS_LOADER_ID = 1
    }
}